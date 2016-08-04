package com.pick;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by 10 on 2016-08-03.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class DetailItems {

    @JsonProperty("data")
    public ArrayList<DetailItem> userDatas;
}
