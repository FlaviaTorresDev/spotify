package flavia.dev.spotify.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import flavia.dev.spotify.cliente.Album;
import flavia.dev.spotify.cliente.AlbumSpotifyClient;
import flavia.dev.spotify.cliente.AuthSpotifyClient;
import flavia.dev.spotify.cliente.LoginRequest;


@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient,
                           AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWorld() {

        var request = new LoginRequest(
                  "client_credentials",
                    "3131728dba644e0296bc42f73e14a91f",
                 "dafcaaff67ed4eea8e47f07af6e75b2e"
        );
        var token = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getReleases("Bearer " + token);


        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}