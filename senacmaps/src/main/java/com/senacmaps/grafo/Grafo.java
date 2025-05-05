package com.senacmaps.grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Grafo {
    Map<String, Vertice> vertices = new HashMap<>();

    public void adicionarVertice(String nome) {
        vertices.putIfAbsent(nome, new Vertice(nome));
    }

    public void adicionarAresta(String origem, String destino, int peso) {
        Vertice vOrigem = vertices.get(origem);
        Vertice vDestino = vertices.get(destino);

        if (vOrigem != null && vDestino != null) {
            // Adiciona a aresta de origem para destino e vice-versa (grafo não direcionado)
            vOrigem.arestas.add(new Aresta(vDestino, peso));
            vDestino.arestas.add(new Aresta(vOrigem, peso));
        }
    }

    public void dijkstra(String origem, String destino) {
        Map<Vertice, Integer> distancias = new HashMap<>();
        Map<Vertice, Vertice> anteriores = new HashMap<>();
        PriorityQueue<VerticeDistancia> fila = new PriorityQueue<>(Comparator.comparingInt(v -> v.distancia));

        Vertice vInicio = vertices.get(origem);

        for (Vertice v : vertices.values()) {
            distancias.put(v, Integer.MAX_VALUE);
        }

        distancias.put(vInicio, 0);
        fila.add(new VerticeDistancia(vInicio, 0));

        while (!fila.isEmpty()) {
            VerticeDistancia atual = fila.poll();
            for (Aresta a : atual.vertice.arestas) {
                int novaDist = distancias.get(atual.vertice) + a.getPeso();

                if (novaDist < distancias.get(a.getDestino())) {
                    distancias.put(a.getDestino(), novaDist);
                    anteriores.put(a.getDestino(), atual.vertice);
                    fila.add(new VerticeDistancia(a.getDestino(), novaDist));
                }
            }
        }

        // Reconstruir o caminho
        List<String> caminho = new ArrayList<>();
        Vertice vFim = vertices.get(destino);

        while (vFim != null) {
            caminho.add(0, vFim.getNome());
            vFim = anteriores.get(vFim);
        }

        // Imprime o menor caminho entre os vértices de origem e destino
        System.out.println("Menor caminho de " + origem + " até " + destino + ": " + caminho);
    }

    private static class VerticeDistancia {
        Vertice vertice;
        int distancia;

        public VerticeDistancia(Vertice vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }
    }
}
