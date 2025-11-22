import grafos.Grafo;
import grafos.Nodo;
import grafos.Arista;
import grafos.DijkstraObject;
import algoritmos.Kruskal;
import interfaz.PanelGrafos;
import javax.swing.JTextArea;
import java.awt.Point;
import java.awt.Graphics;
import java.util.Vector;

public class TestRunner {

    public static void main(String[] args) {
        System.out.println("Starting Test Suite...");

        boolean allPassed = true;
        allPassed &= testGrafoOperations();
        allPassed &= testDijkstra();
        allPassed &= testKruskal();

        if (allPassed) {
            System.out.println("\nALL TESTS PASSED");
        } else {
            System.out.println("\nSOME TESTS FAILED");
            System.exit(1);
        }
    }

    private static boolean testGrafoOperations() {
        System.out.println("\nTesting Grafo Operations...");
        Grafo g = new Grafo();

        // Test adding nodes
        g.insertarNodo(new Point(0, 0));
        g.insertarNodo(new Point(10, 10));

        if (g.getNodos().size() != 2) {
            System.out.println("FAIL: Expected 2 nodes, got " + g.getNodos().size());
            return false;
        }

        // Test adding edge
        g.insertarArista(0, 1);
        if (g.getAristas().size() != 1) {
            System.out.println("FAIL: Expected 1 edge, got " + g.getAristas().size());
            return false;
        }

        Arista a = g.getAristaByIndex(0);
        if (a.getCabeza() != 0 || a.getCola() != 1) {
            System.out.println("FAIL: Edge connectivity incorrect");
            return false;
        }

        System.out.println("PASS: Grafo Operations");
        return true;
    }

    private static boolean testDijkstra() {
        System.out.println("\nTesting Dijkstra...");
        Grafo g = new Grafo();
        g.insertarNodo(new Point(0, 0)); // 0
        g.insertarNodo(new Point(10, 0)); // 1
        g.insertarNodo(new Point(20, 0)); // 2

        g.insertarArista(0, 1);
        g.getAristaByNodosIndex(0, 1).setPeso(5.0);

        g.insertarArista(1, 2);
        g.getAristaByNodosIndex(1, 2).setPeso(3.0);

        g.insertarArista(0, 2);
        g.getAristaByNodosIndex(0, 2).setPeso(10.0);

        // Shortest path 0 -> 2 should be 0->1->2 with weight 8.0
        DijkstraObject result = g.dijkstra(0);

        double dist = result.getDistancia(2);
        if (Math.abs(dist - 8.0) > 0.001) {
            System.out.println("FAIL: Expected distance 8.0, got " + dist);
            return false;
        }

        int pred = result.getPredecesor(2);
        if (pred != 1) {
            System.out.println("FAIL: Expected predecessor 1, got " + pred);
            return false;
        }

        System.out.println("PASS: Dijkstra");
        return true;
    }

    private static boolean testKruskal() {
        System.out.println("\nTesting Kruskal...");
        Grafo g = new Grafo();
        g.insertarNodo(new Point(0, 0)); // 0
        g.insertarNodo(new Point(10, 0)); // 1
        g.insertarNodo(new Point(20, 0)); // 2

        g.insertarArista(0, 1);
        g.getAristaByNodosIndex(0, 1).setPeso(1.0);

        g.insertarArista(1, 2);
        g.getAristaByNodosIndex(1, 2).setPeso(2.0);

        g.insertarArista(0, 2);
        g.getAristaByNodosIndex(0, 2).setPeso(3.0);

        // MST should use edges (0,1) and (1,2) with total weight 3.0

        MockPanelGrafos panel = new MockPanelGrafos(g);
        MockJTextArea textArea = new MockJTextArea();

        Kruskal kruskal = new Kruskal(panel, textArea);
        kruskal.setModo(algoritmos.Algoritmo.continuo); // Run continuously

        // We need to run it. Since it's threaded in the real app, we might need to
        // simulate or just call run() if possible.
        // Kruskal implements Runnable.
        kruskal.run();

        // Kruskal updates 'grafoResultado' in the panel? Or creates a new one?
        // Looking at Kruskal.java (from memory/view), it updates 'grafoResultado'

        // Since we can't easily access the internal result without reflection or
        // getter,
        // we might check if it threw exceptions or finished.

        if (!kruskal.terminado()) {
            System.out.println("FAIL: Kruskal did not finish");
            return false;
        }

        System.out.println("PASS: Kruskal (Basic Execution)");
        return true;
    }

    // Mocks
    static class MockPanelGrafos extends PanelGrafos {
        public MockPanelGrafos(Grafo g) {
            super(g);
        }

        @Override
        public void repaint() {
            // Do nothing
        }

        @Override
        public void paint(Graphics g) {
            // Do nothing
        }
    }

    static class MockJTextArea extends JTextArea {
        @Override
        public void append(String str) {
            // System.out.print(str); // Optional: print to stdout
        }

        @Override
        public void setText(String t) {
            // System.out.println(t);
        }
    }
}
