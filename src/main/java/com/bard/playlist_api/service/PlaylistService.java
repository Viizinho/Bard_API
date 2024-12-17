package com.bard.playlist_api.service;

import com.bard.playlist_api.model.Song;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaylistService {

    private static final String DATA_FILE = "data/playlists.json";
    private Map<String, List<Song>> playlists = new HashMap<>();

    public PlaylistService() {
        loadFromFile(); // Carrega playlists ao iniciar o serviço
    }

    public void createPlaylist(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Playlist name cannot be blank");
        }
        if (playlists.containsKey(name)) {
            throw new IllegalArgumentException("Playlist already exists: " + name);
        }
        playlists.put(name, new ArrayList<>());
        saveToFile();
        System.out.println("Playlist created: " + name);
    }

    public void deletePlaylist(String name) {
        if (!playlists.containsKey(name)) {
            throw new IllegalArgumentException("Playlist does not exist: " + name);
        }
        playlists.remove(name);
        saveToFile();
        System.out.println("Playlist deleted: " + name);
    }

    public List<Song> getPlaylist(String name) {
        return playlists.getOrDefault(name, new ArrayList<>());
    }

    public void addSongToPlaylist(String playlistName, Song song) {
        if (!playlists.containsKey(playlistName)) {
            throw new IllegalArgumentException("Playlist does not exist: " + playlistName);
        }
        playlists.get(playlistName).add(song);
        saveToFile();
        System.out.println("Song added to playlist: " + playlistName);
    }

    public void deleteSongFromPlaylist(String playlistName, String songTitle) {
        if (!playlists.containsKey(playlistName)) {
            throw new IllegalArgumentException("Playlist does not exist: " + playlistName);
        }
        playlists.get(playlistName).removeIf(song -> song.getTitle().equalsIgnoreCase(songTitle));
        saveToFile();
        System.out.println("Song removed from playlist: " + playlistName);
    }

    private void saveToFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(DATA_FILE);
            file.getParentFile().mkdirs(); // Garante que a pasta "data/" exista
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, playlists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(DATA_FILE);
            if (file.exists()) {
                playlists = mapper.readValue(file, new TypeReference<Map<String, List<Song>>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
