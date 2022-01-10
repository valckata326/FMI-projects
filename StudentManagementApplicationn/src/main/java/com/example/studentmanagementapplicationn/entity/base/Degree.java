package com.example.studentmanagementapplicationn.entity.base;

public enum Degree {
    MSC("Master"), BSC("Bachelor"), PHD("PHD");
    private final String name;

    Degree(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Degree getDegree(String name) {
        if(this.getName().equalsIgnoreCase("Master")) {
            return Degree.MSC;
        }
        if(this.getName().equalsIgnoreCase("Bachelor")) {
            return Degree.BSC;
        }
        if(this.getName().equalsIgnoreCase("PHD")) {
            return Degree.PHD;
        }
        return null;
    }
}
