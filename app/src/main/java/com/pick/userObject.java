package com.pick;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by 10 on 2016-08-03.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class userObject {

    @JsonProperty("_id")
    public String userId;
    @JsonProperty("name")
    public String name;

}
