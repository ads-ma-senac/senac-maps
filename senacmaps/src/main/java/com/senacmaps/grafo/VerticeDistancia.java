package com.senacmaps.grafo;

public class VerticeDistancia implements Comparable<VerticeDistancia> {
    Vertice vertice;
    int distancia;

    public VerticeDistancia(Vertice vertice, int distancia) {
        this.vertice = vertice;
        this.distancia = distancia;
    }

    @Override
    public int compareTo(VerticeDistancia outroVertice) {
        return Integer.compare(this.distancia, outroVertice.distancia);
    }
}