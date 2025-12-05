# Algoritmo Ciclo Hamiltoniano

## Restricciones
Grafos **no dirigidos, simples, con pesos positivos y al menos 3 nodos**.

## Conceptos Previos
* **Camino Hamiltoniano**: Camino que contiene a todos los vértices.
* **Ciclo Hamiltoniano**: Ciclo que contiene a todos los vértices.
* **Grafo Hamiltoniano**: Grafo que contiene un ciclo hamiltoniano.

## Ciclo Hamiltoniano de 'Bajo' Peso
Ciclo cuyo peso no supera el doble del peso del ciclo óptimo.
Condiciones:
* $G$ completo, $|V| \ge 3$, pesos positivos.
* $G$ verifica la **desigualdad triangular**.

### Algoritmo basado en Árbol Generador
1. Obtener Árbol Generador de Mínimo Coste ($T$) con Kruskal.
2. Considerar el árbol como grafo dirigido (aristas dobles).
3. Encontrar ciclo euleriano (Hierholzer).
4. Eliminar nodos repetidos para obtener el ciclo hamiltoniano.

### Algoritmo Voraz
1. Seleccionar un vértice inicial.
2. Ir al vértice no visitado más cercano (arista de menor peso).
3. Repetir hasta visitar todos y volver al inicio.

## Ciclo Hamiltoniano de 'Mínimo' Peso
### Fuerza Bruta
1. Buscar todos los ciclos hamiltonianos posibles.
2. Calcular sus pesos.
3. Devolver el de mínimo peso.
