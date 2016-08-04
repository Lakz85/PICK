package com.pick;

/**
 * Created by 10 on 2016-08-03.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailItem {
    @JsonProperty("_id")
    public String id;
    @JsonProperty("type")
    public int type;
    @JsonProperty("genre")
    public String[] genre;
    @JsonProperty("part")
    public String[] part;
    @JsonProperty("cont")
    public String content;
    @JsonProperty("v_key")
    public String videoURL;
    @JsonProperty("u_id")
    public String userId;
    @JsonProperty("userObject")
    public ArrayList<detailUserObject> detailObject;
}
