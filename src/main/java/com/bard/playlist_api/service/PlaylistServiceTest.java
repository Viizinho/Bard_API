package com.bard.playlist_api.service;

import com.bard.playlist_api.model.Song;
import com.bard.playlist_api.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class PlaylistServiceTest {
    public static void main(String[] args) {
        PlaylistService playlistService = new PlaylistService();
        SongRepository songRepository;

        // Testando a criação de playlists
        System.out.println("=== Test: Create Playlists ===");
        playlistService.createPlaylist("My Playlist");
        playlistService.createPlaylist(" "); // Nome inválido
        playlistService.createPlaylist("My Playlist"); // Playlist já existente

        // Testando a adição de músicas
        System.out.println("\n=== Test: Add Songs ===");
        Song song1 = new Song("Song1", "Artist1", "Album1", 300);
        Song song2 = new Song("Song2", "Artist2", "Album2", -5); // Duração inválida
        playlistService.addSongToPlaylist("My Playlist", song1);
        playlistService.addSongToPlaylist("My Playlist", song2); // Música inválida
        playlistService.addSongToPlaylist("Nonexistent Playlist", song1); // Playlist inexistente

        // Testando a exibição de playlists
        System.out.println("\n=== Test: Display Playlists ===");
        System.out.println("My Playlist: " + playlistService.getPlaylist("My Playlist"));
        System.out.println("Nonexistent Playlist: " + playlistService.getPlaylist("Nonexistent Playlist"));

        // Testando a remoção de músicas
        System.out.println("\n=== Test: Remove Songs ===");
        playlistService.deleteSongFromPlaylist("My Playlist", "Song1");
        playlistService.deleteSongFromPlaylist("My Playlist", "Nonexistent Song");

        // Testando a exclusão de playlists
        System.out.println("\n=== Test: Delete Playlists ===");
        playlistService.deletePlaylist("My Playlist");
        playlistService.deletePlaylist("Nonexistent Playlist");
    }
}
