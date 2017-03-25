package com.dev.phil.firebase_tut_1;

/**
 * Created by Phil on 24/03/2017.
 */
public class Golfer{

    private String Name;
    private String Handicap;
    private String eMail;

    public Golfer(String name, String handicap, String eMail) {
        this.Name = name;
        this.Handicap = handicap;
        this.eMail = eMail;
    }

    public Golfer() {}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHandicap() {
        return Handicap;
    }

    public void setHandicap(String handicap) {
        Handicap = handicap;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

}
