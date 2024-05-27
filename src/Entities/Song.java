package Entities;

import uy.edu.um.prog2.adt.HashTable.HashTable;
import uy.edu.um.prog2.adt.HashTable.HashTableImpl;

import java.time.LocalDate;

public class Song {

    private final String spotifyId;

    private final String name;

    private final String artists;

    private final float tempo;

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
