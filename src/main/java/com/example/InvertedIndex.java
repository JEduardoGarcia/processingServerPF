package com.example;

import java.util.*;

public class InvertedIndex {
    // Mapa para almacenar cada palabra clave y sus ubicaciones en los documentos
    private Map<String, List<WordLocation>> index;

    public InvertedIndex() {
        this.index = new HashMap<>();
    }

    // Método para añadir una palabra al índice
    public void addWord(String word, String document, int position) {
        index.computeIfAbsent(word, k -> new ArrayList<>()).add(new WordLocation(document, position));
    }

    // Método para obtener las ubicaciones de una palabra
    public List<WordLocation> getLocations(String word) {
        return index.getOrDefault(word, Collections.emptyList());
    }

    // Método opcional para obtener el índice completo (puede ser útil para debug o pruebas)
    public Map<String, List<WordLocation>> getIndex() {
        return index;
    }
}

// Clase para representar la ubicación de una palabra en un documento
class WordLocation {
    private String document;
    private int position;

    public WordLocation(String document, int position) {
        this.document = document;
        this.position = position;
    }

    public String getDocument() {
        return document;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "WordLocation{" +
                "document='" + document + '\'' +
                ", position=" + position +
                '}';
    }
}
