package com.company;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;


public class AuthorizationCodeUri {
    private static final String clientId = "af40a71a1b9d403291ffd967dc455e6b";
    private static final String clientSecret = "1d899c64117d4060bbb287ee32ed2b46";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify");

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();
    private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
            .state("x4xkmn9pu3j6ukrs8n")
            .scope("user-read-birthdate,user-read-email,user-library-read,playlist-modify-public,playlist-modify-private,")
            .show_dialog(true)
            .build();

    public static String genetateAuthorizationCodeUri() {
        final URI uri = authorizationCodeUriRequest.execute();
        System.out.println("URI: " + uri.toString());
        return uri.toString();
    }


}