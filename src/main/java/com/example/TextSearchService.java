package com.example;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.*;
import java.lang.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;

@Service
public class TextSearchService {

    private final FileLoader fileLoader;
    private final InvertedIndex index;

    public TextSearchService() {
        this.fileLoader = new FileLoader();
        this.index = new InvertedIndex();
    }

    public void indexDocumentsInRange(int start, int end) {
        try {
            List<String> fileNames = fileLoader.listFileNames(start, end);
            for (String fileName : fileNames) {
                String content = fileLoader.loadFileContent(fileName);
                indexDocument(fileName, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void indexDocument(String documentName, String content) {
        String[] words = content.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            index.addWord(words[i], documentName, i);
        }
    }

    public SearchResponse performSearch(int n, List<String> fileNames) throws IOException {
        long startTime = System.currentTimeMillis();
        Map<String, Set<String>> phraseToDocuments = new HashMap<>();

        // Procesar cada documento
        for (String fileName : fileNames) {
            String content = fileLoader.loadFileContent(fileName);
            List<String> phrases = extractPhrases(content, n);
            for (String phrase : phrases) {
                phraseToDocuments.computeIfAbsent(phrase, k -> new HashSet<>()).add(fileName);
            }
        }

        // Filtrar y organizar las frases que aparecen en más de un documento
        List<PhraseMatch> matches = new ArrayList<>();
        phraseToDocuments.forEach((phrase, documents) -> {
            if (documents.size() > 1) { // Solo interesan las frases que aparecen en más de un documento
                matches.add(new PhraseMatch(phrase, new ArrayList<>(documents)));
            }
        });

        // Ordenar las frases alfabéticamente
        matches.sort(Comparator.comparing(PhraseMatch::getPhrase));

        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;

        return new SearchResponse(processingTime, matches);
    }

    private List<String> extractPhrases(String content, int n) {
        List<String> phrases = new ArrayList<>();
        String[] words = content.toLowerCase().replaceAll("[^a-z\\s]", "").split("\\s+");
        for (int i = 0; i <= words.length - n; i++) {
            String[] phraseWords = Arrays.copyOfRange(words, i, i + n);
            phrases.add(String.join(" ", phraseWords));
        }
        return phrases;
    }

    public FileLoader getFileLoader() {
        return fileLoader.getFileLoader();
    }

    // Método para obtener el estado del servidor
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("message", "El servidor está en línea y funcionando.");
        status.put("cpuUsage", getCpuUsage());
        return status;
    }
    
    // Método para obtener el uso de la CPU
    public double getCpuUsage() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        // Esto es una aproximación, puedes ajustar según lo que necesites
        return osBean.getSystemLoadAverage();
    }
}
