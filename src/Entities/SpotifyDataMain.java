package Entities;

import TADs.HashTable.HashTable;
import TADs.HashTable.HashTableImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SpotifyDataMain {
    private HashTable<LocalDate,HashTable<String,HashTable<Integer,Song>>> dailyRanks;
    private HashTable<String,Song> songs;
    private HashTable<String,HashTable<LocalDate,Integer>> artists;

    public SpotifyDataMain() {
        dailyRanks= new HashTableImpl<LocalDate,HashTable<String,HashTable<Integer,Song>>>(370);
        songs= new HashTableImpl<String,Song>(300);
        artists=new HashTableImpl<>(300);

    }

    public HashTable<LocalDate, HashTable<String, HashTable<Integer, Song>>> getDailyRanks() {
        return dailyRanks;
    }

    public HashTable<String, Song> getSongs() {
        return songs;
    }

    public HashTable<String, HashTable<LocalDate, Integer>> getArtists() {
        return artists;
    }

    // row[0]=spotify_id, row[1]=name, row[2]=artists, row[3}daily_rank
    // ,row[7]=country ,row[8]=snapshot_date, row[23]=tempo
    public void readData() throws Exception {
        String file= "universal_top_spotify_songs.csv";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String line2=line.replace("\"","");
            int contador=0;
            while ((line=reader.readLine())!= null){
                // I save the neccesary data in local variables
                line=line.replace(",\"\",","\"\",");
               String[] row = line.split(",\"");
               String spotifyId = row[0].replace("\"","");
               String songName = row[1].replace("\"","");


                //System.out.println(songName);
               String artistName = row[2].replace("\"","");

               Integer dailyRank = Integer.parseInt(row[3].replace("\"",""));

                if(songName.equals("End of Beginning")){
                    contador+=1;
                    if(contador==2076) {
                        dailyRank=3;
                        //2024-03-18
                    }
                    if(contador==2077){
                        System.out.println(2);
                    }
                }


               if (songName.equals("Beautiful Things") & countryName.isEmpty()& dailyRank==2){
                   System.out.println("kdk");
                   // 2024-04-04 , 2024-04-03, 2024-04-27
               }
               String countryName = row[6].replace("\"","");
               if (countryName.isEmpty()){
                   countryName="Global"; // we choose this convention for convenience
               }
               LocalDate date = LocalDate.parse(row[7].replace("\"",""));
               float tempo= Float.parseFloat(row[23].replace("\"",""));
               // check if the song is in the songs hash
                Song song= songs.get(spotifyId);
                if (song==null){
                    song= new Song(spotifyId,songName,artistName,tempo);
                    songs.put(spotifyId,song);
                }
                HashTable<LocalDate,Integer> songRanking= artists.get(artistName);
                // check artists in the singer hash
                if (songRanking==null){ // the artist hasn't been initialized yet
                   HashTable<LocalDate,Integer> artistRanking = new HashTableImpl<LocalDate, Integer>(20);
                   artistRanking.put(date,1); // when we encounter an artist for the first time we add his
                                             // participation in the ranking
                    artists.put(artistName,artistRanking); // we must check the size later
                }
                else { // the singer already existed
                    Integer appearances = songRanking.get(date); // get the amount of times the artist poped up,on a certain date
                    if (appearances==null){
                        songRanking.put(date,1);
                    }
                    else {
                        songRanking.setValueForKey(date,appearances+1);
                    }
                }

                // now begins the triple Hash setup
                HashTable<String,HashTable<Integer,Song>> HashForDate= dailyRanks.get(date);
                if (HashForDate==null){
                    HashForDate= new HashTableImpl<String,HashTable<Integer,Song>>(120); //
                    dailyRanks.put(date,HashForDate);
                }
                HashTable<Integer,Song> RankingDateCountry = HashForDate.get(countryName);
                if (RankingDateCountry==null){
                    RankingDateCountry= new HashTableImpl<Integer,Song>(71);
                    // there are 50 elements but for efficiency we chose 71 to get a loading factor of 0.7 aprox
                    HashForDate.put(countryName,RankingDateCountry);
                }
                RankingDateCountry.put(dailyRank,song);



            }
        }
        catch (Exception _) {
            throw new Exception();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
