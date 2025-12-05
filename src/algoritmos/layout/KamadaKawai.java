package algoritmos.layout;

import grafos.Grafo;
import grafos.Nodo;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class KamadaKawai implements LayoutAlgorithm {

    private double[][] d; // Shortest path distances
    private double[][] l; // Ideal spring lengths
    private double[][] k; // Spring constants
    private double L0; // Desired length of a unit edge
    private double K = 1.0; // Spring constant factor

    @Override
    public void aplicar(Grafo grafo, int width, int height) {
        Vector<Nodo> nodos = grafo.getNodos();
        int N = nodos.size();
        if (N == 0)
            return;

        // 1. Calculate shortest path distances (d_ij) using BFS (assuming unweighted
        // for layout)
        d = new double[N][N];
        for (int i = 0; i < N; i++) {
            bfs(grafo, i, d[i]);
        }

        // 2. Calculate L0 (desired length of unit edge)
        double L = Math.min(width, height) - 50;
        double maxDist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (d[i][j] > maxDist && d[i][j] != Double.MAX_VALUE) {
                    maxDist = d[i][j];
                }
            }
        }
        if (maxDist == 0)
            maxDist = 1;
        L0 = L / maxDist;

        // 3. Calculate ideal lengths (l_ij) and spring constants (k_ij)
        l = new double[N][N];
        k = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j && d[i][j] != Double.MAX_VALUE) {
                    l[i][j] = L0 * d[i][j];
                    k[i][j] = K / (d[i][j] * d[i][j]);
                } else {
                    l[i][j] = 0;
                    k[i][j] = 0;
                }
            }
        }

        // 4. Minimize energy (Simplified iterative approach)
        int iterations = 100;
        for (int iter = 0; iter < iterations; iter++) {
            double maxDelta = 0;
            for (int m = 0; m < N; m++) {
                Point pm = nodos.get(m).getPos();
                double dx = 0;
                double dy = 0;

                for (int i = 0; i < N; i++) {
                    if (m != i && d[m][i] != Double.MAX_VALUE) {
                        Point pi = nodos.get(i).getPos();
                        double distX = pm.x - pi.x;
                        double distY = pm.y - pi.y;
                        double dist = Math.sqrt(distX * distX + distY * distY);
                        if (dist < 0.1)
                            dist = 0.1;

                        double common = k[m][i] * (1 - l[m][i] / dist);
                        dx += common * distX;
                        dy += common * distY;
                    }
                }

                // Move node m to reduce partial derivatives (simple gradient descent step)
                // This is a simplification of the Newton-Raphson method used in original KK
                double moveX = -0.1 * dx; // Learning rate
                double moveY = -0.1 * dy;

                pm.x += moveX;
                pm.y += moveY;

                // Keep within bounds
                pm.x = Math.min(width - 20, Math.max(20, pm.x));
                pm.y = Math.min(height - 20, Math.max(20, pm.y));
            }
        }
    }

    private void bfs(Grafo grafo, int startNode, double[] distances) {
        int N = grafo.getNodos().size();
        for (int i = 0; i < N; i++)
            distances[i] = Double.MAX_VALUE;
        distances[startNode] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            // Find neighbors
            // Grafo structure: Arista stores indices
            for (int i = 0; i < grafo.getAristas().size(); i++) {
                int v = -1;
                if (grafo.getAristas().get(i).getCabeza() == u) {
                    v = grafo.getAristas().get(i).getCola();
                } else if (grafo.getAristas().get(i).getCola() == u) {
                    v = grafo.getAristas().get(i).getCabeza();
                }

                if (v != -1) {
                    if (distances[v] == Double.MAX_VALUE) {
                        distances[v] = distances[u] + 1;
                        queue.add(v);
                    }
                }
            }
        }
    }
}
