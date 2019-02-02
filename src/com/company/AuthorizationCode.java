package com.company;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;

import java.io.IOException;
import java.net.URI;


public class AuthorizationCode {
    private static final String clientId = "e6448fb035b7447d82ffa0892f0300b4";
    private static final String clientSecret = "46678e2e47bd4e119ee92a60c12968fe";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify");


    public static SpotifyApi authorizationCode(String code) {
        final SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUri)
                .build();
        final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();
        try {

            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            // Set access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
            System.out.println(spotifyApi.getAccessToken());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return spotifyApi;
    }


}