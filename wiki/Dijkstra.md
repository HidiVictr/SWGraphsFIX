# Algoritmo de Dijkstra (1959)

## Restricciones
Este algoritmo sólo se puede aplicar a grafos con **pesos positivos**.

## Descripción
Si $G=(V,E)$ es un grafo ponderado positivo, el algoritmo de Dijkstra proporciona el **camino más corto** desde cualquier vértice $v_0$ de $V$ a todos los demás o a uno en concreto.

## Pasos
1. Se parte de un vértice inicial y se calcula cuál es el más próximo.
2. Se fija el vértice no fijado aún que está a una menor distancia del inicial.
3. Se comprueba si utilizando como punto intermedio el último vértice fijado se llega a nuevos vértices o se encuentra un camino más corto a los que ya hemos accedido.
4. Con la información anterior, se actualizan, si procede, las distancias mínimas desde el inicial a cada vértice y cuál es el vértice anterior en el camino más corto desde el inicial a cada uno de ellos.
5. Volvemos al paso 2 hasta que todos los vértices estén fijados.
