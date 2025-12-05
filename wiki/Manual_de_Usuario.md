# Manual de Usuario

La aplicación **SWGraphsFIX** está dividida en las siguientes secciones:

* Barra de botones
* Barra de menús
* Panel de información
* Panel de grafos
* Barra de estado

## Barra de botones

### Botones para manejo de información
* ![abrir](img/2open.png) **ABRIR**: Abre uno de los grafos guardados en XML.
* ![guardar](img/2save.png) **GUARDAR**: Guarda el grafo actual en un archivo XML.

### Botones para ejecutar los algoritmos
* ![ejecutar](img/2play.png) **EJECUTAR**: Ejecuta el algoritmo seleccionado.
* ![porpasos](img/2pasos.png) **POR PASOS**: Permite ejecutar el algoritmo paso a paso (sólo disponible para Edmonds2 y Cartero Chino).

### Botones para introducción de datos
* ![k-grafo](img/2k.png) **K**: Dibuja un grafo completo con el número de nodos indicado.
* ![matriz](img/2matriz.png) **MATRIZ ADYACENCIA**: Abre la matriz de pesos. Si no hay grafo, permite crear uno nuevo desde la matriz.
* ![limpia](img/2limpia.png) **BORRAR PANELES**: Limpia el panel de grafos y de información.

### Ayuda
* ![ayuda](img/2ayuda.png) **AYUDA**: Abre el manual de usuario.

## Opciones de menú

### Menú Archivo
* **ABRIR**: Abre un grafo.
* **GUARDAR**: Guarda el grafo.
* **DEFINIR FONDO**: Establece una imagen de fondo para el panel.
* **BORRAR FONDO**: Elimina la imagen de fondo.
* **SALIR**: Cierra la aplicación.

### Menú Grafo
* **GRADO DE LOS VÉRTICES**: Muestra el grado de los vértices (entrada/salida para dirigidos).
* **GRAFO SUBYACENTE**: Obtiene el grafo subyacente.
* **¿ES CONEXO?**: (Grafos no dirigidos) Indica si es conexo.
* **¿ES FUERTEMENTE CONEXO?**: (Grafos dirigidos) Indica si es fuertemente conexo.
* **¿ES DÉBILMENTE CONEXO?**: (Grafos dirigidos) Indica si es débilmente conexo.
* **GENERAR GRAFO COMPLETO**: Genera un grafo completo K_n.
* **OBTENER MATRIZ DE ADYACENCIA**: Muestra la matriz de adyacencia.
* **MATHEMATICA 4.0**: Genera código para Mathematica.

### Menú Algoritmos
* **EJECUTAR**: Ejecuta el algoritmo seleccionado.
* **POR PASOS**: Ejecución paso a paso.
* **Selección de Algoritmo**: BFS, DFS, Dijkstra, Kruskal, etc.

### Menú Ejemplos
* Carga grafos de ejemplo para los diferentes algoritmos.

### Menú Ayuda
* Acceso a la documentación y "Acerca de".

## Paneles

* **PANEL DE DIBUJO**: Área donde se visualiza y edita el grafo.
* **SELECCIÓN DEL TIPO DE GRAFO**: Panel inferior para elegir entre grafo dirigido/no dirigido y dibujar nodos/aristas.
* **PANEL DE INFORMACIÓN**: Muestra los resultados de los algoritmos.

## Acciones del panel de dibujo

### Creación y Edición
* **DIBUJAR NODO**: Seleccionar "dibujar NODO" y hacer clic izquierdo en el panel.
* **DIBUJAR ARISTA/ARCO**: Seleccionar "dibujar ARISTA/ARCO", pulsar clic izquierdo en un nodo, arrastrar y soltar en otro nodo.
* **BORRAR NODO**: Clic derecho sobre el nodo -> "Borrar el nodo".
* **RENOMBRAR NODO**: Clic derecho sobre el nodo -> "Cambiar el nombre".
* **MOVER NODO**: Arrastrar el nodo con clic izquierdo.
* **BORRAR ARISTA/ARCO**: Clic derecho sobre la arista -> "Borrar arista".
* **CAMBIAR PESO**: Clic derecho sobre la arista -> "Cambiar peso".

### Notas Importantes
* **Peso 0**: Para simular peso 0, use un valor muy pequeño (e.g., 0.001), ya que el 0 se interpreta como ausencia de arista en la matriz.
* **Conversión de Tipos**: Cambiar de grafo dirigido a no dirigido (y viceversa) puede implicar pérdida de información de pesos si no se elige la opción adecuada.
