package com.company;

import com.google.gson.Gson;

public class APIRequestParams {
    private String request;
    private String accessToken;

    public APIRequestParams(String request, String accessToken) {
        this.request = request;
        this.accessToken = accessToken;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
