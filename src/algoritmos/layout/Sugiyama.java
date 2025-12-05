package algoritmos.layout;

import grafos.Grafo;
import grafos.Nodo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;

public class Sugiyama implements LayoutAlgorithm {

    @Override
    public void aplicar(Grafo grafo, int width, int height) {
        Vector<Nodo> nodos = grafo.getNodos();
        int N = nodos.size();
        if (N == 0)
            return;

        // 1. Assign layers (using Longest Path from roots)
        Map<Integer, Integer> layers = new HashMap<>();
        int maxLayer = 0;

        // Find roots (nodes with in-degree 0)
        int[] inDegree = new int[N];
        for (int i = 0; i < grafo.getAristas().size(); i++) {
            int v = grafo.getAristas().get(i).getCabeza(); // Target
            if (v < N)
                inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                layers.put(i, 0);
                queue.add(i);
            }
        }

        // If no roots (cycle), pick node 0
        if (queue.isEmpty()) {
            layers.put(0, 0);
            queue.add(0);
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            int currentLayer = layers.getOrDefault(u, 0);
            maxLayer = Math.max(maxLayer, currentLayer);

            // Find children
            for (int i = 0; i < grafo.getAristas().size(); i++) {
                if (grafo.getAristas().get(i).getCola() == u) {
                    int v = grafo.getAristas().get(i).getCabeza();
                    if (v < N) {
                        int nextLayer = currentLayer + 1;
                        if (nextLayer > layers.getOrDefault(v, -1) && nextLayer < N) { // Limit depth to N to avoid
                                                                                       // cycles
                            layers.put(v, nextLayer);
                            queue.add(v);
                        }
                    }
                }
            }
        }

        // Handle unassigned nodes (disconnected components or cycles)
        for (int i = 0; i < N; i++) {
            if (!layers.containsKey(i)) {
                layers.put(i, 0);
            }
        }

        // 2. Group nodes by layer
        ArrayList<ArrayList<Integer>> nodesByLayer = new ArrayList<>();
        for (int i = 0; i <= maxLayer; i++) {
            nodesByLayer.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            int layer = layers.get(i);
            if (layer <= maxLayer) {
                nodesByLayer.get(layer).add(i);
            }
        }

        // 3. Assign coordinates
        int layerHeight = height / (maxLayer + 2);
        for (int l = 0; l <= maxLayer; l++) {
            ArrayList<Integer> layerNodes = nodesByLayer.get(l);
            int nodesInLayer = layerNodes.size();
            int layerWidth = width / (nodesInLayer + 1);
            int y = (l + 1) * layerHeight;

            for (int k = 0; k < nodesInLayer; k++) {
                int nodeIndex = layerNodes.get(k);
                int x = (k + 1) * layerWidth;

                // Update position
                Point p = nodos.get(nodeIndex).getPos();
                p.x = x;
                p.y = y;
            }
        }
    }
}
