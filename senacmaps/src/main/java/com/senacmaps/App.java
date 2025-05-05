package com.senacmaps;

import com.senacmaps.grafo.Grafo;

public class App {
    public static void main(String[] args) {

        // Cria um grafo
        Grafo grafo = new Grafo();

        // Adiciona vértices
        grafo.adicionarVertice("Portaria 1");
        grafo.adicionarVertice("Portaria 2");
        grafo.adicionarVertice("Auditório");
        grafo.adicionarVertice("Acadêmico");
        grafo.adicionarVertice("Praça de Alimentação 1");
        grafo.adicionarVertice("Praça de Alimentação 2");
        grafo.adicionarVertice("Praça de Alimentação 3");

        // Adiciona arestas com distâncias estimadas
        grafo.adicionarAresta("Portaria 1", "Acadêmico", 40);
        grafo.adicionarAresta("Portaria 1", "Praça de Alimentação 1", 60);
        grafo.adicionarAresta("Acadêmico", "Praça de Alimentação 1", 30);
        grafo.adicionarAresta("Acadêmico", "Praça de Alimentação 2", 35);
        grafo.adicionarAresta("Praça de Alimentação 2", "Auditório", 20);
        grafo.adicionarAresta("Auditório", "Portaria 2", 40);
        grafo.adicionarAresta("Auditório", "Praça de Alimentação 3", 45);
        grafo.adicionarAresta("Praça de Alimentação 3", "Portaria 2", 25);
        grafo.adicionarAresta("Praça de Alimentação 1", "G", 70);
        grafo.adicionarAresta("Acadêmico", "Praça de Alimentação 3", 30);
        grafo.adicionarAresta("Auditório", "Portaria 1", 80);

        // Define os vértices de origem e destino para o cálculo do menor caminho entre
        // os vértices
        String origem = "Acadêmico";
        String destino = "Portaria 2";

        // Executa o algoritmo de Dijkstra e obtém o menor caminho entre os vértices de
        // origem e destino
        grafo.dijkstra(origem, destino);

    }
}
