package Entities;

import com.opencsv.bean.CsvBindByName;

public class Song {

    private String spotifyId;

    private String name;

    private String artists;

    private float tempo;

    public Song(String spotifyId, String name, String artists, float tempo) {
        this.spotifyId = spotifyId;
        this.name = name;
        this.artists = artists;
        this.tempo = tempo;
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
}
