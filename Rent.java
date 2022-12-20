package com.locadora.locadora.Models;

import java.util.Objects;

public class Rent {

    private long timeRent;
    private String emailUserRent;

    public Rent() {
        this.timeRent = 0;
        this.emailUserRent = "";
    }

    public Rent( long timeRent, String emailUserRent) {
        this.timeRent = timeRent;
        this.emailUserRent = emailUserRent;
    }

    public long getTimeRent() {
        return timeRent;
    }

    public void setTimeRent(long timeRent) {
        this.timeRent = timeRent;
    }

    public String getEmailUserRent() {
        return emailUserRent;
    }

    public void setEmailUserRent(String emailUserRent) {
        this.emailUserRent = emailUserRent;
    }

    @Override
    public boolean equals(Object o)
    {
     return ( (o instanceof Rent && (((Rent)o).getEmailUserRent().equals(this.emailUserRent))  ) );   
    }


}
