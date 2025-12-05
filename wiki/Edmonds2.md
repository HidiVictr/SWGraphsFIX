# Algoritmo Edmonds 2

## Restricciones
Este algoritmo sólo se puede aplicar a **grafos no dirigidos y sin bucles**.

## Conceptos Previos
Sea $G=(V,E)$ grafo no dirigido:
* **Emparejamiento**: Subconjunto de $E$, $M$, en el que no hay dos aristas adyacentes y ninguna es un bucle.
* **Vértice M-saturado**: Vértice que posee una arista de $M$ incidente en él.
* **Emparejamiento Máximo**: Emparejamiento tal que no existe ningún otro con mayor número de aristas.
* **Emparejamiento Perfecto**: Emparejamiento en el que todos los vértices son M-saturados.

### Ejemplo
![Emparejamiento](img/ejemploemparejamientos.jpg)

### Emparejamiento Máximo de Máximo Peso
Todo emparejamiento máximo de $G$ tal que el peso de cualquier otro emparejamiento $M_1$ sea menor o igual que el de $M$.

## Algoritmo
El algoritmo de Edmonds 2 obtiene un acoplamiento (emparejamiento) de máxima cardinalidad y peso máximo o mínimo en el grafo dado.

### Visualización
* **Nodos E**: Contorno rojo (relleno rojo si es activo, rojo oscuro si es raíz).
* **Nodos I**: Contorno azul.

### Opciones
* **Maximizar - Minimizar**: Permite elegir si se busca el emparejamiento de máximo o mínimo peso.
