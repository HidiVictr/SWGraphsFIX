# Algoritmo de Kruskal

## Árbol de Expansión Mínima
También llamado árbol generador de mínimo coste.

### Restricciones
Este algoritmo sólo se puede aplicar a **grafos no dirigidos**.

## Conceptos Previos

### Árbol
Se llama árbol a todo grafo no dirigido conexo acíclico.
![Árbol](img/arbol1.jpg)

### Árbol Generador de Mínimo Coste
Sea $G$ un grafo no dirigido conexo ponderado $G=(V,E)$.
* **Árbol Generador**: Subgrafo generador que sea conexo y acíclico.
* **Árbol Generador de Mínimo Coste**: Árbol generador cuyo coste (suma de pesos) sea menor o igual que el de cualquier otro.

> El árbol generador de mínimo coste no tiene por qué ser único.

## Algoritmo
1. Se selecciona, de manera arbitraria, cualquier nodo y se conecta (se selecciona la arista) al nodo más cercano (menor coste) distinto de éste.
2. Se identifica el nodo no conectado más cercano a un nodo conectado, y se conectan estos dos nodos.
    * Si hay nodos sin conectar, ir al Paso 2.
    * En otro caso, FIN.

Los empates se pueden resolver de forma arbitraria.
