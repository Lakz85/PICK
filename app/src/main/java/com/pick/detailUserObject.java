package com.pick;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by 10 on 2016-08-03.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class detailUserObject {

    @JsonProperty("name")
    public String userName;
    @JsonProperty("gender")
    public String userGender;
    @JsonProperty("age")
    public int userAge;
    @JsonProperty("area_do")
    public String area_first;
    @JsonProperty("area_gu")
    public String area_gu;
}
