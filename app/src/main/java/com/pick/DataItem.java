package com.pick;

/**
 * Created by 10 on 2016-08-03.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataItem {
    @JsonProperty("_id")
    public String personId;
    @JsonProperty("type")
    public String personType;
    @JsonProperty("part")
    public String[] personPart;
    @JsonProperty("v_url")
    public String personVideoURL;
    @JsonProperty("userObject")
    public ArrayList<userObject> personName;
}
