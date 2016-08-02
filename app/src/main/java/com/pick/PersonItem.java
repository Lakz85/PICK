package com.pick;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by 10 on 2016-08-03.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonItem {
/*
    @JsonProperty("total")
    public int total;*/

    @JsonProperty("data")
    public ArrayList<DataItem> datas;
}
