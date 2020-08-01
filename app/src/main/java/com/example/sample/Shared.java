package com.example.sample;

import java.io.ByteArrayOutputStream;

class Shared {
    private static String otp = null;
    public static Complaint obj = null;
    private static boolean login = false;
    public static ByteArrayOutputStream b;
    public static String desc,encode;
    public static double longitude, latitude;
    public static String getOtp () {
        if(otp == null)
            throw new NullPointerException("OTP not generated");
        return otp;
    }

    public static void setOtp(String otp) {
        if(otp.length() != 4)
            throw new NumberFormatException("OTP must contain 4 digits only");
        Shared.otp = otp;
    }
    public static void setLogin(){
        login=true;
    }
    public static boolean getLogin(){
        return login;
    }

    }
