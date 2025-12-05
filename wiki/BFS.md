# Algoritmo BFS (Breadth First Search)

## Accesibilidad
Sea $G=(V,E)$ grafo dirigido o grafo no dirigido:
* Se dice que el vértice $u$ alcanza al vértice $v$ en $G$ si $u=v$ o existe una cadena de $u$ a $v$.
* Cada vértice se alcanza a sí mismo.

## Matriz de Acceso
La matriz de acceso de $G$ es la matriz $n \times n$, $A=(a_{ij})$, donde:

![Matriz Acceso](img/matacceso.png)

Todos los elementos de la diagonal principal valen 1.

## Obtención de la Matriz de Acceso
La matriz de acceso se puede obtener mediante la aplicación reiterada de **BFS** o **DFS**.
* Cada fila $i$ de la matriz se obtiene aplicando el algoritmo al vértice $v_i$.
* En grafos no dirigidos, si $u$ alcanza a $v$, entonces $v$ alcanza a $u$.

## Algoritmo de Búsqueda en Anchura
Sea $G=(V,E)$ un grafo.
1. Crea dos listas vacías $L$ y $A$, y una cola vacía $Q$.
2. Elige un vértice $v$, añádelo a $L$ y añade a la cola $Q$ todos los $v'$ tal que $(v, v')$ existe en $E$.
3. Elimina de $Q$ su primer elemento $w$, añádelo a $L$ (al final) y añade a $Q$ todos los vértices $w'$ tales que $(w,w')$ existe en $E$ y que no estén en $L$ ni en $Q$.
4. Añade a $A$ una arista $(w_0, w)$ donde $w_0$ es el primer vértice de $L$ tal que $(w_0, w)$ existe en $E$.
5. Repite los pasos 3 y 4 hasta que $Q$ sea vacía.
