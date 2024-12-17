package com.bard.playlist_api.controller;

import com.bard.playlist_api.model.Song;
import com.bard.playlist_api.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class Controller {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/{name}")
    public void createPlaylist(@PathVariable String name) {
        playlistService.createPlaylist(name);
    }

    @DeleteMapping("/{name}")
    public void deletePlaylist(@PathVariable String name) {
        playlistService.deletePlaylist(name);
    }

    @GetMapping("/{name}")
    public List<Song> getPlaylist(@PathVariable String name) {
        return playlistService.getPlaylist(name);
    }

    @PostMapping("/{playlistName}/songs")
    public void addSongToPlaylist(@PathVariable String playlistName, @RequestBody Song song) {
        playlistService.addSongToPlaylist(playlistName, song);
    }

    @DeleteMapping("/{playlistName}/songs/{songTitle}")
    public void deleteSongFromPlaylist(@PathVariable String playlistName, @PathVariable String songTitle) {
        playlistService.deleteSongFromPlaylist(playlistName, songTitle);
    }
}
