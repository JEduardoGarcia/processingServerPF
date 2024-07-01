package com.example;

import java.util.List;

public class PhraseMatch {
    private String phrase;  // La frase que se encontró
    private List<String> documents;  // Documentos donde la frase fue encontrada

    public PhraseMatch(String phrase, List<String> documents) {
        this.phrase = phrase;
        this.documents = documents;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }
}
