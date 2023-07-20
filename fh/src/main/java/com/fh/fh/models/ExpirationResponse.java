package com.fh.fh.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpirationResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String status;

    @JsonProperty(value = "expires at")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Prague")
    private Instant expiresAt;

    public ExpirationResponse(String status) {
        this.status = status;
    }

    public ExpirationResponse(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}
