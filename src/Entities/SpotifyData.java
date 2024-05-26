package Entities;

import Exceptions.ArtistNotFoundException;
import TADs.HashTable.HashTable;
import TADs.HashTable.HashTableImpl;
import TADs.List.IllegalIndexException;
import TADs.List.List;
import TADs.List.ListImpl;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class SpotifyData {
    private final HashTable<LocalDate, HashTable<String, HashTable<Integer, Song>>> dailyRanks;
    private final HashTable<String, Song> songs;
    private final String[] songKeys;
    private final HashTable<String, HashTable<LocalDate, Integer>> artists;
    private final String[] artistKeys;

    public SpotifyData() {
        dailyRanks = new HashTableImpl<LocalDate, HashTable<String, HashTable<Integer, Song>>>(300);
        songs = new HashTableImpl<String, Song>(15050);
        artists = new HashTableImpl<>(9600);
        songKeys = new String[10516];
        artistKeys = new String[7938];

    }

    public String[] getSongKeys() {
        return songKeys;
    }

    public String[] getArtistKeys() {
        return artistKeys;
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
        int artistCounter = 0;
        int songCounter = 0;
        String file = "universal_top_spotify_songs.csv";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String line2 = line.replace("\"", "");
            int contador = 0;
            while ((line = reader.readLine()) != null) {
                // I save the neccesary data in local variables
                line = line.replace(",\"\",", "\"\",");
                String[] row = line.split(",\"");
                String spotifyId = row[0].replace("\"", "");
                String songName = row[1].replace("\"", "");
                String artistName = row[2].replace("\"", "");
                String[] artistNames = artistName.split(",");
                Integer dailyRank = Integer.parseInt(row[3].replace("\"", ""));
                String countryName = row[6].replace("\"", "");
                if (countryName.isEmpty()) {
                    countryName = "Global"; // we choose this convention for convenience
                }
                LocalDate date = LocalDate.parse(row[7].replace("\"", ""));
                float tempo = Float.parseFloat(row[23].replace("\"", ""));
                // check if the song is in the songs hash
                Song song = songs.get(spotifyId);
                if (song == null) {
                    song = new Song(spotifyId, songName, artistName, tempo);
                    songs.put(spotifyId, song);
                    songKeys[songCounter] = spotifyId;
                    songCounter += 1;
                } else {
                    Integer appearances = song.getAppearances().get(date);
                    if (appearances == null) {
                        song.getAppearances().put(date, 1);
                    } else {
                        song.getAppearances().setValueForKey(date, appearances + 1);
                    }

                }

                // check artists in the singer hash
                for (String name : artistNames) {
                    name = name.trim();
                    HashTable<LocalDate, Integer> songRanking = artists.get(name);
                    if (songRanking == null) { // the artist hasn't been initialized yet
                        HashTable<LocalDate, Integer> artistRanking = new HashTableImpl<LocalDate, Integer>(300);
                        artistRanking.put(date, 1); // when we encounter an artist for the first time we add his
                        // participation in the ranking
                        artists.put(name, artistRanking); // we must check the size later
                        artistKeys[artistCounter] = name;
                        artistCounter += 1;
                    } else { // the singer already existed
                        Integer appearances = songRanking.get(date); // get the amount of times the artist poped up,on a certain date
                        if (appearances == null) {
                            songRanking.put(date, 1);
                        } else {
                            songRanking.setValueForKey(date, appearances + 1);
                        }
                    }
                }

                // now begins the triple Hash setup

                HashTable<String, HashTable<Integer, Song>> HashForDate = dailyRanks.get(date);
                if (HashForDate == null) {
                    HashForDate = new HashTableImpl<String, HashTable<Integer, Song>>(103); //
                    dailyRanks.put(date, HashForDate);
                }
                HashTable<Integer, Song> RankingDateCountry = HashForDate.get(countryName);
                if (RankingDateCountry == null) {
                    RankingDateCountry = new HashTableImpl<Integer, Song>(71);
                    // there are 50 elements but for efficiency we chose 75 to get a loading factor of 0.7 aprox
                    HashForDate.put(countryName, RankingDateCountry);
                }
                while (true) {
                    try {
                        RankingDateCountry.put(dailyRank, song);
                        break;
                    } catch (KeyAlreadyExistsException _) {
                        dailyRank += 1;
                        if (dailyRank == 51) break;
                    }
                }


            }
        } catch (Exception _) {
            throw new Exception();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String findSong(String spotifyId) {
        Song song = songs.get(spotifyId);
        if (song == null) {
            return null;
        }
        return song.getName();
    }

    public void top10DayCountry(LocalDate date, String countryName) {
       HashTable<String,HashTable<Integer,Song>> daily = dailyRanks.get(date);
       if (daily != null) {
           HashTable<Integer, Song> dailyTop = daily.get(countryName);
           for (int i = 1; i < 11; i++) {
               System.out.println(i + "-  " + dailyTop.get(i).getName() + ",  ");
           }
       }
    }

    public void top5SongsInTop50(LocalDate date) throws IllegalIndexException {
        String top1 = null;
        Integer appearancesTop1 = 0;
        String top2 = null;
        Integer appearancesTop2 = 0;
        String top3 = null;
        Integer appearancesTop3 = 0;
        String top4 = null;
        Integer appearancesTop4 = 0;
        String top5 = null;
        Integer appearancesTop5 = 0;
        for (int i = 0; i < songKeys.length; i++) {
            if (songKeys[i] != null) {
                Song tempSong = songs.get(songKeys[i]);
                Integer appearances = tempSong.getAppearances().get(date);
                if (appearances != null) {
                    if (appearances > appearancesTop1) {
                        top5 = top4;
                        top4 = top3;
                        top3 = top2;
                        top2 = top1;
                        top1 = tempSong.getName();
                        appearancesTop5 = appearancesTop4;
                        appearancesTop4 = appearancesTop3;
                        appearancesTop3 = appearancesTop2;
                        appearancesTop2 = appearancesTop1;
                        appearancesTop1 = appearances;
                    } else if (appearances > appearancesTop2) {
                        top5 = top4;
                        top4 = top3;
                        top3 = top2;
                        top2 = tempSong.getName();
                        appearancesTop5 = appearancesTop4;
                        appearancesTop4 = appearancesTop3;
                        appearancesTop3 = appearancesTop2;
                        appearancesTop2 = appearances;
                    } else if (appearances > appearancesTop3) {
                        top5 = top4;
                        top4 = top3;
                        top3 = tempSong.getName();
                        appearancesTop5 = appearancesTop4;
                        appearancesTop4 = appearancesTop3;
                        appearancesTop3 = appearances;
                    } else if (appearances > appearancesTop4) {
                        top5 = top4;
                        top4 = tempSong.getName();
                        appearancesTop5 = appearancesTop4;
                        appearancesTop4 = appearances;
                    } else if (appearances > appearancesTop5) {
                        top5 = tempSong.getName();
                        appearancesTop5 = appearances;

                    }
                }
            }
        }
        System.out.println("Top 5: " + top5);
        System.out.println("Top 4: " + top4);
        System.out.println("Top 3: " + top3);
        System.out.println("Top 2: " + top2);
        System.out.println("Top 1: " + top1);
    }


    public void top7Artist(LocalDate beginningDate, LocalDate endDate) throws IllegalIndexException {
        String top1 = null;
        Integer appearancesTop1 = 0;
        String top2 = null;
        Integer appearancesTop2 = 0;
        String top3 = null;
        Integer appearancesTop3 = 0;
        String top4 = null;
        Integer appearancesTop4 = 0;
        String top5 = null;
        Integer appearancesTop5 = 0;
        String top6 = null;
        Integer appearancesTop6 = 0;
        String top7 = null;
        Integer appearancesTop7 = 0;

        for (String artist : artistKeys) {
            LocalDate currentDate = beginningDate;
            Integer appearances = 0;
            if (artist != null) {
                while (currentDate.isBefore(endDate.plusDays(1))) {
                    Integer temp = artists.get(artist).get(currentDate);
                    if (temp != null) {
                        appearances += temp;
                    }
                    currentDate = currentDate.plusDays(1);
                }

                if (appearances > appearancesTop1) {
                    top7 = top6;
                    top6 = top5;
                    top5 = top4;
                    top4 = top3;
                    top3 = top2;
                    top2 = top1;
                    top1 = artist;
                    appearancesTop7 = appearancesTop6;
                    appearancesTop6 = appearancesTop5;
                    appearancesTop5 = appearancesTop4;
                    appearancesTop4 = appearancesTop3;
                    appearancesTop3 = appearancesTop2;
                    appearancesTop2 = appearancesTop1;
                    appearancesTop1 = appearances;
                } else if (appearances > appearancesTop2) {
                    top7 = top6;
                    top6 = top5;
                    top5 = top4;
                    top4 = top3;
                    top3 = top2;
                    top2 = artist;
                    appearancesTop7 = appearancesTop6;
                    appearancesTop6 = appearancesTop5;
                    appearancesTop5 = appearancesTop4;
                    appearancesTop4 = appearancesTop3;
                    appearancesTop3 = appearancesTop2;
                    appearancesTop2 = appearances;
                } else if (appearances > appearancesTop3) {
                    top7 = top6;
                    top6 = top5;
                    top5 = top4;
                    top4 = top3;
                    top3 = artist;
                    appearancesTop7 = appearancesTop6;
                    appearancesTop6 = appearancesTop5;
                    appearancesTop5 = appearancesTop4;
                    appearancesTop4 = appearancesTop3;
                    appearancesTop3 = appearances;
                } else if (appearances > appearancesTop4) {
                    top7 = top6;
                    top6 = top5;
                    top5 = top4;
                    top4 = artist;
                    appearancesTop7 = appearancesTop6;
                    appearancesTop6 = appearancesTop5;
                    appearancesTop5 = appearancesTop4;
                    appearancesTop4 = appearances;
                } else if (appearances > appearancesTop5) {
                    top7 = top6;
                    top6 = top5;
                    top5 = artist;
                    appearancesTop7 = appearancesTop6;
                    appearancesTop6 = appearancesTop5;
                    appearancesTop5 = appearances;
                } else if (appearances > appearancesTop6) {
                    top7 = top6;
                    top6 = artist;
                    appearancesTop7 = appearancesTop6;
                    appearancesTop6 = appearances;
                } else if (appearances > appearancesTop7) {
                    top7 = artist;
                    appearancesTop7 = appearances;
                }
            }

        }
        System.out.println("7 : " + top7);
        System.out.println("6 : " + top6);
        System.out.println("5 : " + top5);
        System.out.println("4 : " + top4);
        System.out.println("3 : " + top3);
        System.out.println("2 : " + top2);
        System.out.println("1 : " + top1);
    }

    public int artistInDate(String artistName, LocalDate date) throws ArtistNotFoundException {
        HashTable<LocalDate, Integer> artistHash = artists.get(artistName);
        if (artistHash == null) {
            throw new ArtistNotFoundException();
        }
        Integer appearances = artistHash.get(date);
        if (appearances == null) {
            return 0;
        }
        return appearances;
    }

    public int tempoInDate(LocalDate beginningDate, LocalDate endDate, float tempoLow, float tempoHigh) throws IllegalIndexException {
        int contador = 0;
        LocalDate currentDate = beginningDate;
        List<String> countries = dailyRanks.get(LocalDate.parse("2024-04-19")).keys();
        while (currentDate.isBefore(endDate.plusDays(1))) {
            HashTable<String, HashTable<Integer, Song>> dailyRank = dailyRanks.get(currentDate);
            if (dailyRank != null) {
                for (int i = 0; i < countries.size(); i++) {
                    String con = countries.get(i);
                    HashTable<Integer, Song> countryDaily = dailyRank.get(con);
                    if (countryDaily != null) {
                        for (int j = 1; j < 51; j++) {
                            Song song = countryDaily.get(j);
                            if (song != null) {
                                if (song.getTempo() >= tempoLow && song.getTempo() <= tempoHigh) {
                                    contador += 1;
                                }
                            }
                        }
                    }
                }
            }
            currentDate = currentDate.plusDays(1);
        }
        return contador;
    }
}

