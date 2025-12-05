# Orientabilidad (Hopcroft-Tarjan)

## Definición
Un grafo no dirigido es **orientable** si podemos asignar un sentido a cada arista de manera que el grafo dirigido resultante sea **fuertemente conexo**.

### Teorema de Robbins
Un grafo no dirigido es orientable si y sólo si es **conexo y no tiene puentes**.

## Algoritmo
1. Elegir un vértice inicial $v_1$.
2. Aplicar **DFS** a $v_1$.
3. Orientar las aristas del árbol DFS en la dirección de la búsqueda.
4. Para las aristas que no pertenecen al árbol DFS (aristas de retroceso), orientarlas desde el descendiente hacia el ancestro (según el orden de visita).
