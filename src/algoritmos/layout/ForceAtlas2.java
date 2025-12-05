package algoritmos.layout;

import grafos.Grafo;
import grafos.Nodo;
import java.awt.Point;
import java.util.Vector;

public class ForceAtlas2 implements LayoutAlgorithm {

    private int iterations = 100;
    private double scalingRatio = 2.0;
    private double gravity = 1.0;
    private boolean strongGravityMode = false;

    @Override
    public void aplicar(Grafo grafo, int width, int height) {
        Vector<Nodo> nodos = grafo.getNodos();
        int N = nodos.size();
        if (N == 0)
            return;

        double centerX = width / 2.0;
        double centerY = height / 2.0;

        // Initialize displacements
        double[] dispX = new double[N];
        double[] dispY = new double[N];

        for (int iter = 0; iter < iterations; iter++) {
            // Reset displacements
            for (int i = 0; i < N; i++) {
                dispX[i] = 0;
                dispY[i] = 0;
            }

            // 1. Repulsion (between all pairs)
            for (int n1 = 0; n1 < N; n1++) {
                for (int n2 = 0; n2 < N; n2++) {
                    if (n1 != n2) {
                        Point p1 = nodos.get(n1).getPos();
                        Point p2 = nodos.get(n2).getPos();
                        double dx = p1.x - p2.x;
                        double dy = p1.y - p2.y;
                        double dist = Math.sqrt(dx * dx + dy * dy);
                        if (dist < 0.1)
                            dist = 0.1;

                        // ForceAtlas2 repulsion: k * (deg1 + 1) * (deg2 + 1) / dist
                        // Simplified: 100 / dist
                        double factor = 100.0 / dist;
                        dispX[n1] += (dx / dist) * factor;
                        dispY[n1] += (dy / dist) * factor;
                    }
                }
            }

            // 2. Attraction (edges)
            for (int i = 0; i < grafo.getAristas().size(); i++) {
                int n1 = grafo.getAristas().get(i).getCabeza();
                int n2 = grafo.getAristas().get(i).getCola();

                if (n1 >= N || n2 >= N)
                    continue;

                Point p1 = nodos.get(n1).getPos();
                Point p2 = nodos.get(n2).getPos();
                double dx = p1.x - p2.x;
                double dy = p1.y - p2.y;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if (dist < 0.1)
                    dist = 0.1;

                // ForceAtlas2 attraction: dist
                // Simplified: dist / scalingRatio
                double factor = dist / scalingRatio;

                double dx_att = (dx / dist) * factor;
                double dy_att = (dy / dist) * factor;

                dispX[n1] -= dx_att;
                dispY[n1] -= dy_att;
                dispX[n2] += dx_att;
                dispY[n2] += dy_att;
            }

            // 3. Gravity
            for (int n = 0; n < N; n++) {
                Point p = nodos.get(n).getPos();
                double dx = p.x - centerX;
                double dy = p.y - centerY;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if (dist > 0) {
                    double factor = gravity * (strongGravityMode ? scalingRatio : 1.0);
                    dispX[n] -= (dx / dist) * factor;
                    dispY[n] -= (dy / dist) * factor;
                }
            }

            // Apply displacements
            for (int n = 0; n < N; n++) {
                Point p = nodos.get(n).getPos();
                p.x += dispX[n];
                p.y += dispY[n];

                // Keep within bounds
                p.x = Math.min(width - 20, Math.max(20, p.x));
                p.y = Math.min(height - 20, Math.max(20, p.y));
            }
        }
    }
}
