# SWGraphsFIX - Graph Algorithms & Visualization

This project is a Java-based application for creating, visualizing, and analyzing graphs. It provides a graphical interface to draw nodes and edges, and runs various graph algorithms (Dijkstra, Kruskal, Hamilton cycles, etc.).

## ⚠️ Alpha / Experimental Version

This is the first public release of this fork.

**Important Technical Details:**
* 🛠 **Origin:** This project is a fork created through reverse engineering (decompilation) of the original `v2.2.0` `.jar`.
* 🤖 **AI-Generated Code:** Significant portions of the reconstruction and new features have been generated using Artificial Intelligence.
* 🧪 **Status:** The code has **NOT been 100% audited**. It "works", but may contain bugs, unexpected behavior, or instability in edge cases.

---
**USE AT YOUR OWN RISK.** Not recommended for exams, assignments, or critical coursework.

## ⚖️ Credits & Legal Context
This project is a **fork** and **restoration** of **SWGraphs**, originally created as a Final Degree Project (TFG) at the **Universitat Politècnica de València (UPV)**.

*   **Original Author:** Jose Chamorro Molina
*   **Director:** Cristina Jordán Lluch
*   **Original Version:** 2.1.9 (approx. 2010-2012)

**License Status:**
The original software was distributed without an explicit license (e.g., MIT, GPL). This fork is created for **educational and maintenance purposes** to keep the tool functional on modern systems.
*   **Rights:** All original rights belong to the original author.
*   **Modifications:** New features and fixes in this "FIX" branch are contributed by Victor Fernandez Saez.

If you are the original author and wish to discuss this repository, please open an issue.

## 🏗 System Architecture

The application follows a variation of the **Model-View-Controller (MVC)** pattern.

### 1. The Model (`grafos` package)
This layer is responsible for storing the raw data of the graph.
*   **`Grafo.java`**: The central class. It maintains a list of `Nodo` objects and `Arista` (Edge) objects. It provides methods to add/remove elements and retrieve graph properties (adjacency matrix, etc.).
*   **`Nodo.java`**: Represents a vertex. It stores its position (`Point`), name, and visual properties.
*   **`Arista.java`**: Represents an edge. It stores the indices of the start (`cabeza`) and end (`cola`) nodes, weight, and type (directed/undirected).

### 2. The View (`interfaz` package)
This layer handles the rendering of the graph on the screen.
*   **`PanelGrafos.java`**: The base panel for rendering.
    *   **`paintComponent(Graphics g)`**: The main rendering loop. It clears the screen and calls `Grafo.pintarGrafo(g)`.
    *   **Zoom & Pan**: It manages the `zoom` factor and translation offsets (`tx`, `ty`) to transform the view.
*   **`Grafo.pintarGrafo(...)`**: The actual drawing logic. It iterates through all nodes and edges, applies the current zoom/translation to their coordinates, and draws them using Java 2D Graphics (`Graphics2D`).

### 3. The Controller (`interfaz` package)
This layer handles user input and updates the model.
*   **`PanelGrafosDibujo.java`** (Extends `PanelGrafos`):
    *   **`MouseListener` / `MouseMotionListener`**: Detects clicks and drags.
    *   **`mouseDragged`**: When a node is dragged, it updates the node's position in the `Grafo` model and calls `repaint()`.
    *   **`mouseWheelMoved`**: Updates the zoom factor.
    *   **`keyPressed`**: Handles keyboard shortcuts (e.g., 'm' for panning).
*   **`MenuPrincipal.java`**: The main application window (`JFrame`). It contains the menu bar for selecting algorithms and file operations.

### 4. Algorithms (`algoritmos` package)
Each algorithm is encapsulated in its own class (e.g., `Kruskal.java`, `Dijkstra.java`).
*   They typically run on a separate thread (implementing `Runnable`) to avoid freezing the UI.
*   They interact with the `Grafo` model to read structure and write results (e.g., highlighting edges).
*   They call `panel.repaint()` to visualize progress or results.

## 🔄 How It Works: The Rendering Loop

1.  **User Action**: You drag a node with the mouse.
2.  **Controller**: `PanelGrafosDibujo.mouseDragged` calculates the new coordinate.
    *   It applies the inverse of the current zoom/translation to map screen pixels back to graph coordinates.
    *   It updates the `Nodo` object inside `Grafo`.
    *   It calls `this.repaint()`.
3.  **System**: Java schedules a repaint request.
4.  **View**: `PanelGrafos.paintComponent` is called.
5.  **Model Draw**: `Grafo.pintarGrafo` is called.
    *   It reads the *new* position of the node.
    *   It reads the connected edges.
    *   It calculates the screen positions for the node and its edges (applying zoom/pan).
    *   It draws the lines connecting the new node positions.

## 🚀 Key Features
*   **Semantic Zoom**: Zoom in/out towards the mouse cursor.
*   **Panning**: Move the canvas using the Middle Mouse Button or 'm' + Drag.
*   **Graph Editing**: Create/Delete nodes and edges, change weights.
*   **Algorithms**: Run standard graph algorithms and visualize steps.
