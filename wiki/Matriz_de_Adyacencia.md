# Matriz del Grafo

## 1. Matriz de Adyacencia
Llamamos matriz de adyacencia de $G=(V,E)$ a la matriz $n \times n$, $A=(a_{ij})$.

![Matriz Adyacencia](img/matady.png)

### Ejemplo
![Grafo Ejemplo](img/mat_ady.jpg)
![Matriz Ejemplo](img/ady9.png)

## 2. Matriz de Acceso
(Ver ayuda de los algoritmos [BFS](BFS) o [DFS](DFS)).

## 3. Matriz de Pesos
Este diálogo le permite modificar la matriz de pesos del grafo para etiquetar de forma rápida un gran número de aristas/arcos.

### Uso
* Para etiquetar una arista/arco $(i,j)$, sitúese en la celda intersección (fila $i$, columna $j$) e introduzca el valor.
* Pulse Intro o seleccione otra celda para guardar.
* Etiquetar con peso **0.0** equivale a borrar la arista.

> **Grafos No Dirigidos**: Si modifica $(i,j)$, se actualiza automáticamente $(j,i)$.
> **Grafos Dirigidos**: Cada posición es independiente.

## 4. Matriz de Multiplicidades (Hierholzer)
Matriz $n \times n$ donde cada valor indica el número de aristas (arcos) que van desde el nodo $i$ al nodo $j$.
* Se utiliza en el algoritmo de **Hierholzer** para especificar los **s-grafos**.
* Se activa automáticamente al seleccionar dicho algoritmo.

## 5. Matriz de Capacidades (Flujo Máximo)
Matriz $n \times n$ donde cada valor indica la capacidad de la arista (arco) que va desde el nodo $i$ al nodo $j$.
* Se utiliza en el algoritmo de **Ford-Fulkerson** (Flujo Máximo).
* Se activa automáticamente al seleccionar dicho algoritmo.
