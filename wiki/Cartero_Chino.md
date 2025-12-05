# Algoritmo del Cartero Chino

## Restricciones
Grafos **no dirigidos, sin bucles y con pesos positivos**.

## Descripción
Busca un recorrido de **mínimo peso** que recorra **todas las aristas** del grafo, comenzando y terminando en el mismo vértice.

## Funcionamiento
El algoritmo hace uso de tres sub-algoritmos:
1. **Edmonds 2**: Para emparejar vértices de grado impar (si los hay) y hacer el grafo euleriano.
2. **Dijkstra**: Para encontrar los caminos más cortos entre los vértices emparejados.
3. **Fleury / Hierholzer**: Para encontrar el ciclo euleriano en el grafo resultante.
