package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class TextSearchController {

    @Autowired
    private TextSearchService textSearchService;

    @GetMapping("/search")
    public SearchResponse search(
            @RequestParam int n,
            @RequestParam int startDocumentIndex,
            @RequestParam int endDocumentIndex) {
        try {
            // Indexar documentos en el rango específico
            textSearchService.indexDocumentsInRange(startDocumentIndex, endDocumentIndex);

            // Obtener los nombres de los archivos
            List<String> fileNames = textSearchService.getFileLoader().listAllFileNames();
            // Realizar la búsqueda con el número de palabras n
            return textSearchService.performSearch(n, fileNames.subList(startDocumentIndex, endDocumentIndex + 1));
        } catch (IOException e) {
            e.printStackTrace();
            return new SearchResponse(0, List.of()); // Devolver una respuesta vacía en caso de error
        }
    }

    @GetMapping("/status")
    public String getStatus() {
        return textSearchService.getStatus();
    }

    @GetMapping("/cpu-usage")
    public double getCpuUsage() {
        return textSearchService.getCpuUsage();
    }
}
