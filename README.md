# Programming II Assignment
Authors: Santiago De Giuda, Felipe Guasch
Proceso de carga de datos : 

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

1. Top 10 Canciones por País y Fecha
Se utiliza el método top10SongsByCountryAndDate(String country, LocalDate date) el cual obtiene el HashTable correspondiente a la fecha date desde dailyRanks. Luego, se accede al HashTable correspondiente al país. Se crea una list<Song> donde se van guardando las canciones, se itera del 1 al 10 para obtener la canción correspondiente desde el HashTable del país.


2. Top 5 Canciones en más países para un Día Específico


3. Top 7 Artistas por Rango de Fechas



4. Cantidad de Apariciones de un Artista en una Fecha Específica



5. Cantidad de Canciones en un Rango de Tempo y Fechas

<img width="171" alt="RAM Usage 3rd Function" src="https://github.com/PipeG09/Obligatorio-Prog-II/assets/103771722/2b6eadfb-97ba-46b3-b3af-78f036112074">

![Results](https://github.com/PipeG09/Obligatorio-Prog-II/assets/103771722/d5db257a-4194-446e-9b0f-42a22fd87031)



