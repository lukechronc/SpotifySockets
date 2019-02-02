package com.company;

public class APIRequestResponse {
    private String request;
    private String body;

    public APIRequestResponse(String request, String body) {
        this.request = request;
        this.body = body;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
