package com.senacmaps.grafo;

import java.util.ArrayList;
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
        Vertice verticeOrigem = vertices.get(origem);
        Vertice verticeDestino = vertices.get(destino);

        if (verticeOrigem != null && verticeDestino != null) {
            // Adiciona a aresta de origem para destino e vice-versa (grafo não direcionado)
            verticeOrigem.arestas.add(new Aresta(verticeDestino, peso));
            verticeDestino.arestas.add(new Aresta(verticeOrigem, peso));
        }
    }

    public void dijkstra(String origem, String destino) {
        Map<Vertice, Integer> menoresDistancias = new HashMap<>();
        Map<Vertice, Vertice> caminhoPrevio = new HashMap<>();

        // Fila de prioridade para armazenar os vértices a visitar
        // A fila é ordenada pela distância do vértice atual
        // A classe VerticeDistancia é usada para armazenar o vértice e sua distância
        // A fila de prioridade garante que o vértice com a menor distância seja
        // processado primeiro
        // A classe Comparator é usada para comparar as distâncias dos vértices
        PriorityQueue<VerticeDistancia> verticesAVisitar = new PriorityQueue<>();

        // Verifica se os vértices de origem e destino existem no grafo
        // Se não existirem, imprime uma mensagem de erro e retorna
        Vertice verticeInicio = vertices.get(origem);
        Vertice verticeFim = vertices.get(destino);

        if (verticeInicio == null || verticeFim == null) {
            System.out.println("Vértice de origem ou destino não encontrado.");
            return;
        }

        // Inicializa as distâncias e o mapa de vértices visitados com valores máximos
        for (Vertice v : vertices.values()) {
            menoresDistancias.put(v, Integer.MAX_VALUE);
        }

        // A distância do vértice de origem para ele mesmo é 0
        menoresDistancias.put(verticeInicio, 0);

        // Adiciona o vértice de origem à fila de prioridade
        verticesAVisitar.add(new VerticeDistancia(verticeInicio, 0));

        // Enquanto houver vértices a visitar
        while (!verticesAVisitar.isEmpty()) {

            // Remove o vértice com a menor distância
            VerticeDistancia atual = verticesAVisitar.poll();

            // Atualiza as distâncias dos vizinhos
            for (Aresta a : atual.vertice.arestas) {
                int novaDist = menoresDistancias.get(atual.vertice) + a.getPeso();

                if (novaDist < menoresDistancias.get(a.getDestino())) {
                    // Atualiza a distância e o vértice anterior
                    menoresDistancias.put(a.getDestino(), novaDist);

                    // Atualiza o caminho anterior
                    caminhoPrevio.put(a.getDestino(), atual.vertice);

                    // Adiciona o vizinho à fila de prioridade
                    verticesAVisitar.add(new VerticeDistancia(a.getDestino(), novaDist));
                }
            }
        }

        // Reconstruir o caminho
        List<String> caminho = new ArrayList<>();

        while (verticeFim != null) {
            caminho.add(0, verticeFim.getNome());
            verticeFim = caminhoPrevio.get(verticeFim);
        }

        // Imprime o menor caminho entre os vértices de origem e destino
        System.out.println("Menor caminho de " + origem + " até " + destino + ": " + caminho);
    }

}
