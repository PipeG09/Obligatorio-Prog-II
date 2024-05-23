package Entities;

import java.util.HashMap;

public class Top {
    private final HashMap<Integer, Song> topSongs;

    public Top() {
        topSongs = new HashMap<>();
    }

    public void addSong(int position, Song song) {
        topSongs.put(position, song);
    }

    public Song getSong(int position) {
        return topSongs.get(position);
    }

    @Override
    public String toString() {
        return "Top{" +
                "topSongs=" + topSongs +
                '}';
    }
}



