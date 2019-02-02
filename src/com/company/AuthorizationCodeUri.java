package com.company;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;


public class AuthorizationCodeUri {
    private static final String clientId = "e6448fb035b7447d82ffa0892f0300b4";
    private static final String clientSecret = "46678e2e47bd4e119ee92a60c12968fe";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify");

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();
    private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
            .state("x4xkmn9pu3j6ukrs8n")
            .scope("user-read-birthdate,user-read-email")
            .show_dialog(true)
            .build();

    public static String genetateAuthorizationCodeUri() {
        final URI uri = authorizationCodeUriRequest.execute();
        System.out.println("URI: " + uri.toString());
        return uri.toString();
    }


}