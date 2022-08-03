package com.bharadwaj.samhith.lineage.test.models;

import java.util.List;

public class Member {
    private String Name ;

    private int BirthYear;
    private int DeathYear;
    private List<Member> Members;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getBirthYear() {
        return BirthYear;
    }

    public void setBirthYear(int birthYear) {
        BirthYear = birthYear;
    }

    public int getDeathYear() {
        return DeathYear;
    }

    public void setDeathYear(int deathYear) {
        DeathYear = deathYear;
    }

    public List<Member> getMembers() {
        return Members;
    }

    public void setMembers(List<Member> members) {
        Members = members;
    }
}
