package com.complaint.infrastructure.location;

public class CountryIsResponse {

    private String ip;

    private String country;

    public CountryIsResponse() {}

    public CountryIsResponse(String ip, String country) {
        this.ip = ip;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

}
