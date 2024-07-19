package it.guidera.gabriele.dto;

import java.util.Date;

public class UtenteLoginResponseDTO {
    private String token;
    private Date tokenCreationTime;
    private Date ttl; //Time to leave


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenCreationTime() {
        return tokenCreationTime;
    }

    public void setTokenCreationTime(Date tokenCreationTime) {
        this.tokenCreationTime = tokenCreationTime;
    }

    public Date getTtl() {
        return ttl;
    }

    public void setTtl(Date ttl) {
        this.ttl = ttl;
    }
}
