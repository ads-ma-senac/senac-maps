package com.senacmaps.grafo;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private final String nome;
    public List<Aresta> arestas = new ArrayList<>();

    public Vertice(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
