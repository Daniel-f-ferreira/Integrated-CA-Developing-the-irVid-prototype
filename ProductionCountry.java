package com.locadora.locadora.Models;

public class ProductionCountry {
    
    private String iso31661;
    private String name;

    public ProductionCountry(String iso31661, String name)
    {
        this.iso31661 = iso31661;
        this.name = name;
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
