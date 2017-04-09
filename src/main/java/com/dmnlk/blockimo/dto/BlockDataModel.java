package com.dmnlk.blockimo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlockDataModel {
    @JsonProperty("twitterId")
    private Long twitterId;
    @JsonProperty("url")
    private  String url;
}
