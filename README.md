# Programming II Assignment
Authors: Santiago De Giuda, Felipe Guasch

# Proceso de carga de datos : 

Se crea una clase SpotifyData la cual en la funcion readData(). Para leer el csv se utiliza BufferedReader para leer linea por linea haciendo un split con (",") y quedándonos  con los datos que se quieren cargar.

Las estructuras de datos son las siguientes:

dailyRanks: Este es un HashTable donde la key es del tipo LocalDate y el value es otro HashTable. El segundo HashTable tiene como key un String que hace referencia al país y tiene como value un tercer HashTable. El último HashTable tiene como key un atributo del tipo int, que representa el número del ranking en el que está, y como value la instancia de la clase Song correspondiente para el ranking. Con este HashTable se puede acceder a un top de un día y un país en O(1), lo que reduce el tiempo de la mayoría de nuestras búsquedas.

songs: Este es un HashTable donde la key es un String correspondiente al SpotifyId de la canción y el value es la instancia del objeto Song. Este HashTable permite buscar una canción teniendo el SpotifyID en O(1). Esto es de gran utilidad en una variedad de escenarios, un claro ejemplo es a la hora de ingresar una canción, ya que es muy rápido verificar si la canción ya se encuentra en el programa.

songKeys: Este es un arreglo de Strings (String[]). Este guarda los SpotifyID para que más adelante sea más fácil iterar y para tener todas las keys con fácil acceso.

artists: Este es un HashTable que tiene como key un dato del tipo String, el cual corresponde al nombre de un artista, y como value otro HashTable. Este segundo HashTable tiene como key una LocalDate y como value un Integer, siendo el Integer la cantidad de veces que apareció la canción en ese día. Este HashTable permite hacer una variedad de consultas en tiempo O(1).

artistKeys: Este es un arreglo de Strings (String[]) en donde se guardan todos los nombres de los artistas.

La creación de estas estructuras de datos se realiza leyendo una sola vez cada línea del CSV, promediando un tiempo de carga de 2.5 segundos y consumiendo alrededor de 200 MB de memoria RAM.

<img width="171" alt="RAM Usage Loading DAta" src="https://github.com/PipeG09/Obligatorio-Prog-II/assets/103771722/d8acca43-2658-464a-868d-68540530e6db">

Proceso de Realización de Reportes
Los reportes se generan a través de métodos en la clase SpotifyData, que son invocados desde el main en SpotifyApp.java. 

#1. Top 10 Canciones por País y Fecha

Se utiliza el método top10SongsByCountryAndDate(String country, LocalDate date) el cual obtiene el HashTable correspondiente a la fecha date desde dailyRanks. Luego, se accede al HashTable correspondiente al país. Se crea una list<Song> donde se van guardando las canciones, se itera del 1 al 10 para obtener la canción correspondiente desde el HashTable del país.


#2. Top 5 Canciones en más países para un Día Específico

Se utiliza el método top5SongsInTop50 el cual inicializa 2 list<String> tops (Es el nombre de la canción), list<Integer> appearencesList (guarda el número de apariciones de una canción que se encuentra también en la lista de tops). Se itera sobre songKeys para obtener todas las canciones, para cada canción se obtiene la instancia de Song desde el HashTable y luego se obtiene el número de apariciones de la canción desde el HashTable appearances para el día específico.
Nos fijamos si tiene más apariciones que la quinta cancion de la lista, si es así se continua iterando hasta encontrar la posición en la cual tiene menos que el siguiente, y se ingresa en esa posición, en ambas list (en cancion se ingresa el nombre de la canción y en apppearencesList se ingresa las apariciones). Si se llega hasta la primer posicion entonces se ingresa la cancion en el top 1. Cada vez que se introduce una nueva canción se elimina la última quedandonos así siempre con solo 5 canciones.


#3. Top 7 Artistas por Rango de Fechas

Utilizando principalmente el artists HashTable, para cada Artista se itera sobre la fechas partiendo de la menor fecha hasta llegar a la fecha final del rango pedido, 
   sumando la cantidad de veces que aparecio para cada fecha y luego de calcular esto nos fijamos si pertenece al top 7 de artsitas con mas apariciones hasta el momento de la 
   iteracion.
   Esto se hace de la siguiente manera: 
           1. Nos fijamos si tiene mas apariciones que el septimo artista del Top, Si este no es el caso seguimos con el proximo Artista.
           2. Si tiene mas apariciones que el septimo, lo comparamos con el 6to y si tiene menos que el sexto ingresamos el artista de la itracion actual en el puesto 7 del top
           3. Sino seguimo subiendo en el ranking hasta econtrar un artista con mas apariciones que el actual para ingresarlo por debajo de este
           4. Si se llega hasta el primero y el Artista actual tiene mas apariciones se ingresa primero en el Top.
   Este metodo de ordenamiento nos ofrece una gran eficiencia ya que comparamos la cancion de la itracion actual lo menos posible con los Artistas de la lista y reducimos la cantidad de iteraciones sobre el Top.

#4. Cantidad de Apariciones de un Artista en una Fecha Específica

Utilizando el HashTable de artists, se obtiene un HashTable el cual tiene como key la fecha y como value las apariciones en esa fecha. Simplemente se piden las apariciones y se retornan.


#5. Cantidad de Canciones en un Rango de Tempo y Fechas

Para esta funcion se utiliza el HashTable DailyRankings ya que se itera sobre las fechas, accediendo para cada fecha al Ranking top 50 de cada pais y ahi fijandonos si      el tempo las canciones esta dentro del rango y en ese caso se suma 1 al contador. Para no contar una cancion dos veces nos creamos un HashTable temporal en el cual nos
   guardamo las instancias de la clase Songs usando el SpotifyId como key del Hash, ingresando al Hash al sumar al contador. De esta manera antes de sumar 1 al contador nos
   fijamos que la cancion no se encuentra ya en el hash temporal para evitar contar 2 veces la misma cancion.

<img width="171" alt="RAM Usage 3rd Function" src="https://github.com/PipeG09/Obligatorio-Prog-II/assets/103771722/2b6eadfb-97ba-46b3-b3af-78f036112074">

![Results](https://github.com/PipeG09/Obligatorio-Prog-II/assets/103771722/d5db257a-4194-446e-9b0f-42a22fd87031)



