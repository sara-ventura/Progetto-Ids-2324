package it.unicam.cs.ids.GeoPlus.Model.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RispostaAut {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("message")
    private String message;

    public RispostaAut(String accessToken, String refreshToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
        this.refreshToken = refreshToken;
    }


}
