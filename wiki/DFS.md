# Algoritmo DFS (Depth First Search)

## Accesibilidad
Sea $G=(V,E)$ grafo dirigido o grafo no dirigido:
* Se dice que el vértice $u$ alcanza al vértice $v$ en $G$ si $u=v$ o existe una cadena de $u$ a $v$.
* Cada vértice se alcanza a sí mismo.

## Matriz de Acceso
La matriz de acceso de $G$ es la matriz $n \times n$, $A=(a_{ij})$, donde:
* $a_{ij} = 1$, si el vértice $v_i$ alcanza al vértice $v_j$.
* $a_{ij} = 0$, si el vértice $v_i$ no alcanza al vértice $v_j$.

Todos los elementos de la diagonal principal valen 1.

## Algoritmo de Búsqueda en Profundidad
Sea $G=(V,E)$ un grafo.
1. Crea dos listas vacías $L$ y $A$, y una pila vacía $P$.
2. Elige un vértice $v$ y añádelo a $L$ y a $P$.
3. Sea $w$ el elemento más alto de la pila $P$.
    * Si existe un vértice $w'$ que no esté en $L$ y $(w,w')$ existe en $E$, añádelo a $P$ y a $L$, y añade a $A$ la arista/arco $(w,w')$.
    * En caso contrario elimina $w$ de $P$.
4. Repite el paso 3 hasta que la pila $P$ esté vacía.
