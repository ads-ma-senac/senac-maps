package com.senacmaps.grafos.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Grafo<T> {
    private final ArrayList<Vertice<T>> vertices;
    private final ArrayList<Aresta<T>> arestas;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    public void adicionarVertice(T dado) {
        Vertice<T> novoVertice = new Vertice<>(dado);
        this.vertices.add(novoVertice);
    }

    public void adicionarAresta(Double peso, T dadoInicio, T dadoFim) {
        Vertice<T> inicio = this.getVertice(dadoInicio);
        Vertice<T> fim = this.getVertice(dadoFim);
        Aresta<T> aresta = new Aresta<>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<T> getVertice(T dado) {
        Vertice<T> vertice = null;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getDado().equals(dado)) {
                vertice = this.vertices.get(i);
                break;
            }
        }

        return vertice;
    }

    public void buscarEmLargura() {
        ArrayList<Vertice<T>> marcados = new ArrayList<>();
        ArrayList<Vertice<T>> fila = new ArrayList<>();
        Vertice<T> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);

        while (!fila.isEmpty()) {
            Vertice<T> visitado = fila.get(0);
            for (int i = 0; i < visitado.getArestasSaida().size(); i++) {
                Vertice<T> proximo = visitado.getArestasSaida().get(i).getFim();
                if (!marcados.contains(proximo)) {
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }

            fila.remove(0);
        }
    }

    public List<String> dijkstra(T inicio, T fim) {
        Map<Vertice<T>, Double> distancias = new HashMap<>();
        Map<Vertice<T>, Vertice<T>> anteriores = new HashMap<>();
        PriorityQueue<VerticeDistancia<T>> fila = new PriorityQueue<>(Comparator.comparingDouble(v -> v.distancia));
        
        Vertice<T> vInicio = this.getVertice(inicio);
        
        for (Vertice<T> v : this.vertices) {
            distancias.put(v, Double.MAX_VALUE);
        }

        distancias.put(vInicio, 0.0);
        fila.add(new VerticeDistancia<>(vInicio, 0.0));

        while (!fila.isEmpty()) {
            VerticeDistancia<T> atual = fila.poll();

            for (Aresta<T> a : atual.getVertice().getArestasSaida()) {
                Double novaDist = distancias.get(atual.vertice) + a.getPeso();

                if (novaDist < distancias.get(a.getFim())) {
                    distancias.put(a.getFim(), novaDist);
                    anteriores.put(a.getFim(), atual.vertice);
                    fila.add(new VerticeDistancia<>(a.getFim(), novaDist));
                }
            }
        }

        List<String> caminho = new ArrayList<>();
        Vertice<T> vFim = this.getVertice(fim);

        while (vFim != null) {
            caminho.add(0, vFim.getDado().toString());
            vFim = anteriores.get(vFim);
        }

        if (caminho.isEmpty()) {
            System.out.println("Caminho não encontrado.");
            return caminho;
        }

        System.out.println("Caminho encontrado: " + String.join(" -> ", caminho));
        System.out.println("Distância total: " + distancias.get(this.getVertice(fim)));

        return caminho;
    }

    private static class VerticeDistancia<T> {
        Vertice<T> vertice;
        Double distancia;

        public VerticeDistancia(Vertice<T> vertice, Double distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }

        public Vertice<T> getVertice() {
            return vertice;
        }
    }

}
