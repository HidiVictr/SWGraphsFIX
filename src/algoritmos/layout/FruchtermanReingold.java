package algoritmos.layout;

import grafos.Grafo;
import grafos.Nodo;
import java.awt.Point;
import java.util.Vector;

public class FruchtermanReingold implements LayoutAlgorithm {

    private double k;
    private double temp;
    private int iterations = 100;
    private int width;
    private int height;

    @Override
    public void aplicar(Grafo grafo, int width, int height) {
        this.width = width;
        this.height = height;
        Vector<Nodo> nodos = grafo.getNodos();
        int nodeCount = nodos.size();
        if (nodeCount == 0)
            return;

        double area = width * height;
        k = Math.sqrt(area / nodeCount);
        temp = width / 10.0;

        // Initialize displacements
        double[] dispX = new double[nodeCount];
        double[] dispY = new double[nodeCount];

        for (int i = 0; i < iterations; i++) {
            // Calculate repulsive forces
            for (int v = 0; v < nodeCount; v++) {
                dispX[v] = 0;
                dispY[v] = 0;
                for (int u = 0; u < nodeCount; u++) {
                    if (v != u) {
                        Point pv = nodos.get(v).getPos();
                        Point pu = nodos.get(u).getPos();
                        double dx = pv.x - pu.x;
                        double dy = pv.y - pu.y;
                        double delta = Math.sqrt(dx * dx + dy * dy);
                        if (delta < 0.1)
                            delta = 0.1; // Avoid division by zero

                        double repulsion = (k * k) / delta;
                        dispX[v] += (dx / delta) * repulsion;
                        dispY[v] += (dy / delta) * repulsion;
                    }
                }
            }

            // Calculate attractive forces
            for (int j = 0; j < grafo.getAristas().size(); j++) {
                int vIndex = grafo.getAristas().get(j).getCabeza();
                int uIndex = grafo.getAristas().get(j).getCola();

                // Ensure indices are valid
                if (vIndex >= nodeCount || uIndex >= nodeCount)
                    continue;

                Point pv = nodos.get(vIndex).getPos();
                Point pu = nodos.get(uIndex).getPos();
                double dx = pv.x - pu.x;
                double dy = pv.y - pu.y;
                double delta = Math.sqrt(dx * dx + dy * dy);
                if (delta < 0.1)
                    delta = 0.1;

                double attraction = (delta * delta) / k;

                double dispX_att = (dx / delta) * attraction;
                double dispY_att = (dy / delta) * attraction;

                dispX[vIndex] -= dispX_att;
                dispY[vIndex] -= dispY_att;
                dispX[uIndex] += dispX_att;
                dispY[uIndex] += dispY_att;
            }

            // Limit displacement by temperature and update positions
            for (int v = 0; v < nodeCount; v++) {
                Point p = nodos.get(v).getPos();
                double dx = dispX[v];
                double dy = dispY[v];
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist > 0) {
                    double limitedDist = Math.min(dist, temp);
                    p.x += (dx / dist) * limitedDist;
                    p.y += (dy / dist) * limitedDist;

                    // Keep within bounds
                    p.x = Math.min(width - 20, Math.max(20, p.x));
                    p.y = Math.min(height - 20, Math.max(20, p.y));
                }
            }

            // Cool down
            temp *= 0.95;
        }
    }
}
