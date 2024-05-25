package Entities;

import TADs.HashTable.HashTable;
import TADs.HashTable.HashTableImpl;
import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;

public class Song {

    private String spotifyId;

    private String name;

    private String artists;

    private float tempo;
    private HashTable<LocalDate, Integer> appearances;

    public Song(String spotifyId, String name, String artists, float tempo) {
        this.spotifyId = spotifyId;
        this.name = name;
        this.artists = artists;
        this.tempo = tempo;
        this.appearances = new HashTableImpl<>(100);
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public String getName() {
        return name;
    }

    public String getArtists() {
        return artists;
    }

    public float getTempo() {
        return tempo;
    }

    public HashTable<LocalDate, Integer> getAppearances() {
        return appearances;
    }
}
