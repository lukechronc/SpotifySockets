package com.company;

public class APIRequestHandler {
    private String request;
    private String userId;
    private String accessToken;

    public APIRequestHandler(String request, String userId, String accessToken) {
        this.request = request;
        this.userId = userId;
        this.accessToken = accessToken;

    }

    public String execute() {
        String requestString = null;
        switch (request) {
            case "getURI":
                requestString = AuthorizationCodeUri.genetateAuthorizationCodeUri();
                break;
            case "generateAccessToken":
                requestString = AuthorizationCode.authorizationCode(accessToken).getAccessToken();
                break;

            default:
                requestString = "Error: Invalid request. Request \"" + request + "\" with userId: " + userId;
                break;

        }
        return requestString;
    }

}
