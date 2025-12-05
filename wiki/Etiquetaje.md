# Algoritmo de Ford-Fulkerson (Flujo Máximo)

## Restricciones
Este algoritmo sólo se puede aplicar a **grafos dirigidos débilmente conexos**.

## Conceptos Básicos

### Red N(G,s,t,c)
* $G$: Grafo dirigido débilmente conexo.
* $s$: Vértice fuente (grado salida > 0).
* $t$: Vértice sumidero (grado entrada > 0).
* $c$: Función capacidad.

![Red](img/redes_t3.png)

### Flujo
Función $f: E(G) \to \mathbb{N} \cup \{0\}$ que verifica:
1. **Limitación por capacidad**: $0 \le f(e) \le c(e)$ para todo $e$.
2. **Conservación**: Para todo nodo intermedio, el flujo que entra es igual al que sale.

### Flujo de la Red
Valor neto que sale de la fuente (o entra al sumidero).

### Grafo Residuo
Grafo que representa las capacidades residuales (lo que falta por llenar o lo que se puede vaciar).

## Algoritmo
1. Inicializar flujo $f=0$.
2. Construir grafo residual $G_f$.
3. Mientras exista un camino de aumento de $s$ a $t$ en $G_f$:
    * Encontrar la capacidad residual mínima ($\delta$) en el camino.
    * Aumentar el flujo en $\delta$.
    * Actualizar capacidades residuales.
4. El flujo máximo es la suma de los aumentos.
