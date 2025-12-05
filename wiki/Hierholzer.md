# Algoritmo de Hierholzer

## Matriz del Grafo
Utiliza la **Matriz de Multiplicidades**.

## S-Grafo
Multigrafo donde el mayor número de aristas entre dos vértices es $s$.
![S-Grafo](img/sgrafos.png)

## Ciclos Eulerianos
* **Cadena Euleriana**: Recorre todas las aristas una sola vez.
* **Ciclo Euleriano**: Cadena euleriana cerrada.

### Condiciones de Existencia
* **Grafo No Dirigido**: Todos los vértices tienen grado par.
* **Grafo Dirigido**: Para todo vértice, grado de entrada = grado de salida.

## Algoritmo
1. Se fija un vértice $v$ y se busca un ciclo.
2. Si quedan aristas sin visitar, se busca un vértice del ciclo con aristas incidentes no visitadas.
3. Se genera un nuevo ciclo desde ese vértice y se empalma con el anterior.
4. Repetir hasta cubrir todas las aristas.
