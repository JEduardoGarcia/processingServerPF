package com.example;

import java.util.List;

public class SearchResponse {
    private long processingTime; // Tiempo de procesamiento de la búsqueda
    private List<PhraseMatch> matches; // Lista de coincidencias encontradas

    public SearchResponse(long processingTime, List<PhraseMatch> matches) {
        this.processingTime = processingTime;
        this.matches = matches;
    }

    public long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
    }

    public List<PhraseMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<PhraseMatch> matches) {
        this.matches = matches;
    }
}
