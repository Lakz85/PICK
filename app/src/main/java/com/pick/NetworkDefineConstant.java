package com.pick;

/**
 * Created by 10 on 2016-07-21.
 */
public class NetworkDefineConstant {

    public static final String HOST_URL = "52.78.95.102";
    public static final int PORT_NUMBER= 3000;
    public static final String PERSON_LIST = "/persons";
    public static final String SERVER_URL_PERSON_LIST_VIEW=
            "http://"+HOST_URL+":"+PORT_NUMBER+PERSON_LIST;
    public static final String SERVER_URL_BLOOD_ALL_SELECT = "http://"+HOST_URL+":5678/androidNetwork/bloodInsert.pyo";
    public static final int BLOOD_INSERT_DIALOG_OK = 1;
    public static final int BLOOD_INSERT_DIALOG_FAIL=2;

}
