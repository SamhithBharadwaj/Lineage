package com.bharadwaj.samhith.lineage.test;

import com.bharadwaj.samhith.lineage.test.models.FamilyLineage;
import com.bharadwaj.samhith.lineage.test.models.Lineage;
import com.bharadwaj.samhith.lineage.test.models.Member;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBuilder {

    private Random random = new Random();

    public Lineage buildLineageObject(){
        Lineage lineage = new Lineage();
        FamilyLineage familyLineage = new FamilyLineage();
        lineage.setLineage(familyLineage);
        familyLineage.setFamilyTree("Family Tree");

        List<Member> members = new ArrayList<>();
        int era = getRandomIntI(400) * getRandomIntI(4);
        for (int i = 0; i < 180 + getRandomInt4(); i++) {
            era += getRandomIntI(10);
//            members.add(createMembers("Name" + i, 80 + getRandomInt6(), era));
        }
        familyLineage.setMembers(members);
        return lineage;
    }

    public Lineage buildLineageObjectWithGenerator(JsonGenerator generator) throws IOException {
        generator.writeStartObject();
        generator.writeObjectFieldStart("lineage");
        Lineage lineage = new Lineage();
        FamilyLineage familyLineage = new FamilyLineage();
        lineage.setLineage(familyLineage);
        familyLineage.setFamilyTree("Family Tree");
        generator.writeStringField("FamilyTree","Family Tree");
        generator.writeArrayFieldStart("Members");

        List<Member> members = new ArrayList<>();
        int era = getRandomIntI(400) * getRandomIntI(4);
        for (int i = 0; i < 180 + getRandomInt4(); i++) {
            era += getRandomIntI(10);
           createMembers("Name" + i, 80 + getRandomInt6(), era,generator);
        }
        familyLineage.setMembers(members);
        generator.writeEndArray();
        generator.writeEndObject();
        generator.writeEndObject();
        generator.close();
        return lineage;
    }

    private Member createMembers(String name, int counter, int era,JsonGenerator generator) throws IOException {
        if (counter == 0) {
            return null;
        }
        generator.writeStartObject();
        generator.writeStringField("Name",name + counter + getRandomInt10());
        Member member = new Member();
        member.setName(name + counter + getRandomInt10());
        int birth = era;
        generator.writeObjectField("BirthYear",birth);
        generator.writeObjectField("DeathYear",birth + getRandomInt100());
        member.setBirthYear(birth);
        member.setDeathYear(birth + getRandomInt100());
        counter--;
        generator.writeArrayFieldStart("Members");
        generator.flush();
//        List<Member> members = new ArrayList<>();
        for (int i = 0; i < getRandomInt4(); i++) {
            era += getRandomIntI(10);
            Member member1 = createMembers(name, counter, era,generator);
//            if (member1 != null) {
//                members.add(member1);
//            }
        }
        generator.writeEndArray();
        generator.writeEndObject();
//        member.setMembers(members);
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

    private int getRandomIntI(int i) {
        return random.nextInt(i);
    }
}
