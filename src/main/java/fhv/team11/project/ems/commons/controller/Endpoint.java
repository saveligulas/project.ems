package fhv.team11.project.ems.commons.controller;

public enum Endpoint {
    ERROR("error");

    private final String endpoint;

    Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public String getRedirectUri() {
        return "redirect:/" + this.endpoint;
    }
}
