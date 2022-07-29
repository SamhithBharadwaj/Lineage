package com.bharadwaj.samhith.lineage.test;

import com.bharadwaj.samhith.lineage.test.models.FamilyLineage;
import com.bharadwaj.samhith.lineage.test.models.Lineage;
import com.bharadwaj.samhith.lineage.test.models.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBuilder {

    private Random random = new Random();
    public Lineage buildLineageObject() {
        Lineage lineage=new Lineage();
        FamilyLineage familyLineage = new FamilyLineage();
        lineage.setLineage(familyLineage);
        familyLineage.setFamilyTree("Family Tree");
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 2+getRandomInt4(); i++) {
            members.add(createMembers("Name" + i, 2+getRandomInt6(), getRandomInt400() * getRandomInt4()));
        }
        familyLineage.setMembers(members);
        return lineage;
    }

    private Member createMembers(String name, int counter, int era) {
        if (counter == 0) {
            return null;
        }
        Member member = new Member();
        member.setName(name + counter + getRandomInt10());
        int birth = era + getRandomInt400();
        member.setBirthYear(birth);
        member.setDeathYear(birth + getRandomInt100());
counter--;
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < getRandomInt4(); i++) {
            Member member1=createMembers(name, counter, era);
            if(member1!=null){
                members.add(member1);
            }
        }
        member.setMembers(members);
        return member;
    }

    private int getRandomInt10() {
        return random.nextInt(10);
    }

    private int getRandomInt4() {
        return random.nextInt(4);
    }
    private int getRandomInt6() {
        return random.nextInt(6);
    }


    private int getRandomInt100() {
        return random.nextInt(100);
    }

    private int getRandomInt400() {
        return random.nextInt(400);
    }
}
