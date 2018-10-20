package com.github.ankurpathak.springsession;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {

    private Map<String, Object> extras = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getExtras() {
        return extras;
    }


    @JsonAnySetter
    public ApiResponse addExtra(String name, Object value) {
        if(this.extras != null)
            this.extras.put(name, value);
        return this;



    }


    public ApiResponse addExtras(Map<String, Object> extras) {
        if(this.extras != null)
            this.extras.putAll(extras);
        return this;
    }


    private ApiResponse(ApiCode code, String message){
        this.extras.put("code", code.getCode());
        this.extras.put("message", message);
    }

    public static ApiResponse getInstance(@JsonProperty("code") ApiCode code, @JsonProperty("message") String message){
        return new ApiResponse(code, message);
    }


    public ApiResponse() {
    }
}
