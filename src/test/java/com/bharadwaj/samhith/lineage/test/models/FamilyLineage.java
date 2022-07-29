package com.bharadwaj.samhith.lineage.test.models;

import java.util.List;

public class FamilyLineage {
    private String FamilyTree;
    private List<Member> Members;

    public String getFamilyTree() {
        return FamilyTree;
    }

    public void setFamilyTree(String familyTree) {
        FamilyTree = familyTree;
    }

    public List<Member> getMembers() {
        return Members;
    }

    public void setMembers(List<Member> members) {
        Members = members;
    }
}
