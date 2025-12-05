# Generalidades

## Definiciones Básicas

### Grafos No Dirigidos
Sea $G$ un grafo no dirigido. Si $(u,v)$ es una arista se dice que:
* $u$ y $v$ son vértices **adyacentes**.
* $(u,v)$ es **incidente** en $u$ y $v$.
* Si $u=v$ se dice que $(u,v)$ es un **bucle**.

### Grafos Dirigidos
Sea $G$ un grafo dirigido. Si $(u,v)$ es un arco se dice que:
* $u$ es **extremo inicial** de $(u,v)$.
* $v$ es **extremo final** de $(u,v)$.
* Si $u=v$ se dice que $(u,v)$ es un **bucle**.

### Otros Conceptos
* **Grafo Simple**: Grafo que no posee bucles.
* **Subgrafo**: Un grafo $H=(V(H),E(H))$ es subgrafo de $G=(V(G),E(G))$ si $V(H) \subseteq V(G)$ y $E(H) \subseteq E(G)$.
* **Subgrafo Generador**: Si $V(H)=V(G)$.
* **Subgrafo Inducido**: Subgrafo de $G$ cuyo conjunto de vértices es $V'$ y es maximal respecto al conjunto de aristas.

## Grados

### Grafo No Dirigido
El **grado** de un vértice $v$, denotado $d(v)$, es el número de aristas incidentes en él.
> Nota: Si en $v$ hay un bucle, éste añade 2 unidades al cómputo total del grado.

### Grafo Dirigido
* **Grado de entrada** $d_e(v)$: Número de arcos que tienen por extremo final $v$.
* **Grado de salida** $d_s(v)$: Número de arcos que tienen por extremo inicial $v$.

## Grafo Subyacente
El grafo subyacente de un grafo dirigido $G$ es el grafo no dirigido obtenido al sustituir todo arco $(u,v)$ por una arista $(u,v)$.
