package com.company;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.library.GetUsersSavedTracksRequest;
import com.wrapper.spotify.requests.data.playlists.AddTracksToPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

import java.io.IOException;
import java.util.ArrayList;


public class PlaylistFromSavedTracks {
    private String accessToken;
    private GetUsersSavedTracksRequest getUsersSavedTracksRequest;
    private CreatePlaylistRequest createPlaylistRequest;
    private GetCurrentUsersProfileRequest getCurrentUsersProfileRequest;
    private AddTracksToPlaylistRequest addTracksToPlaylistRequest;
    private SpotifyApi spotifyApi;

    public PlaylistFromSavedTracks(String accessToken) {
        this.accessToken = accessToken;

        this.spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();

        this.getUsersSavedTracksRequest = spotifyApi.getUsersSavedTracks()
                .limit(50)
                .offset(0)
                .market(CountryCode.SE)
                .build();

        this.getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile()
                .build();


        this.createPlaylistRequest = spotifyApi.createPlaylist(getCurrentUsersID(), "Saved songs")
                .collaborative(false)
                .public_(false)
                .user_id(getCurrentUsersID())
                .name("Saved songs")
                .description("Saved songs")
                .build();



    }


    public ArrayList<String> getUsersSavedTracks() {
        try {
            final Paging<SavedTrack> savedTrackPaging = getUsersSavedTracksRequest.execute();
            SavedTrack[] savedTracks = savedTrackPaging.getItems();
            ArrayList<String> tracks = new ArrayList<>();

            for (SavedTrack st : savedTracks) tracks.add(st.getTrack().getUri());
            //String result = "";
            //for (String s : tracks) result = result + s + ", ";
            //System.out.println(result);
            return tracks;


        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public void createPlaylist(ArrayList<String> uris) {
        try {
            final Playlist playlist = createPlaylistRequest.execute();
            String[] uris_array = new String[uris.size()];
            uris_array = uris.toArray(uris_array);

            addTracksToPlaylistRequest = spotifyApi
                    .addTracksToPlaylist(playlist.getId(), uris_array)
                    .position(0)
                    .build();

            final SnapshotResult snapshotResult = addTracksToPlaylistRequest.execute();

            System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());

            System.out.println("Name: " + playlist.getName());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private String getCurrentUsersID() {
        try {
            final User user = getCurrentUsersProfileRequest.execute();
            System.out.println("userID: " + user.getId());
            return user.getId();
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}