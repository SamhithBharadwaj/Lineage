package com.bharadwaj.samhith.lineage.models;

import java.util.List;

public class FamilyLineage {
    private String familyTree;
    private List<Member> members;

    public String getFamilyTree() {
        return familyTree;
    }

    public void setFamilyTree(String familyTree) {
        this.familyTree = familyTree;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "FamilyLineage{" +
                "familyTree='" + familyTree + '\'' +
                ", members=" + members +
                '}';
    }
}
