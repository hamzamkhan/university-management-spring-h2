package com.hamzamustafakhan.springh2student.util;

public class Utility {

    public static String parseEmail(String email){
        email = email.replace("\"","");
        email = email.replace("\n", "");
        email = email.replace("{","");
        email = email.replace("}","");
        email = email.trim();
        return email;
    }
}
