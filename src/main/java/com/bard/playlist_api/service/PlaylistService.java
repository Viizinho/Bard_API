package com.bard.playlist_api.service;

import com.bard.playlist_api.model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaylistService {

    private Map<String, List<Song>> playlists = new HashMap<>();

    public void createPlaylist(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Invalid playlist name. Please provide a valid name.");
            return;
        }

        if (playlists.containsKey(name)) {
            System.out.println("Playlist already exists: " + name);
            return;
        }

        playlists.put(name, new ArrayList<>());
        System.out.println("Playlist created: " + name);
    }

    public void deletePlaylist(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Invalid playlist name. Please provide a valid name.");
            return;
        }

        if (!playlists.containsKey(name)) {
            System.out.println("Playlist not found: " + name);
            return;
        }

        playlists.remove(name);
        System.out.println("Playlist deleted: " + name);
    }

    public List<Song> getPlaylist(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Invalid playlist name. Please provide a valid name.");
            return new ArrayList<>();
        }

        if (!playlists.containsKey(name)) {
            System.out.println("Playlist not found: " + name);
            return new ArrayList<>();
        }

        return playlists.get(name);
    }

    public void addSongToPlaylist(String playlistName, Song song) {
        if (playlistName == null || playlistName.trim().isEmpty()) {
            System.out.println("Invalid playlist name. Please provide a valid name.");
            return;
        }

        if (!playlists.containsKey(playlistName)) {
            System.out.println("Playlist not found: " + playlistName);
            return;
        }

        if (song == null || song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            System.out.println("Invalid song details. Please provide a valid song title.");
            return;
        }

        if (song.getDuration() <= 0) {
            System.out.println("Invalid song duration. Duration must be positive.");
            return;
        }

        playlists.get(playlistName).add(song);
        System.out.println("Song added to playlist: " + playlistName);
    }


    public void deleteSongFromPlaylist(String playlistName, String songTitle) {
        if (playlistName == null || playlistName.trim().isEmpty()) {
            System.out.println("Invalid playlist name. Please provide a valid name.");
            return;
        }

        if (!playlists.containsKey(playlistName)) {
            System.out.println("Playlist not found: " + playlistName);
            return;
        }

        if (songTitle == null || songTitle.trim().isEmpty()) {
            System.out.println("Invalid song title. Please provide a valid song title.");
            return;
        }

        List<Song> playlist = playlists.get(playlistName);
        boolean removed = playlist.removeIf(song -> song.getTitle().equalsIgnoreCase(songTitle));

        if (removed) {
            System.out.println("Song removed from playlist: " + playlistName);
        } else {
            System.out.println("Song not found in playlist: " + playlistName);
        }
    }

}
