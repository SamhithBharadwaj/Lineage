package com.bharadwaj.samhith.lineage.service;

import com.bharadwaj.samhith.lineage.exceptions.InvalidDataException;
import com.bharadwaj.samhith.lineage.models.Member;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MemberObjectValidator {

    public List<Member> validateAndReturnMembers(JSONObject object) {
        Object members = object.get("Members");
        List<Member> membersList = new ArrayList<>();
        if (members != null) {
            for (Object o : (JSONArray) members) {
                try {
                    membersList.add(validateAndParseMember((JSONObject) o));
                } catch (InvalidDataException e) {
                    //log issue
                }
            }

        }
        return membersList;
    }

    private String validateAndParseName(JSONObject obj) {
        JSONObject name = (JSONObject) obj.get("Name");
        if (name == null) throw new InvalidDataException("Name is null");
        String nameStr = name.toString();
        return nameStr;

    }

    private int validateAndParseBirthYear(JSONObject obj) {
        JSONObject year = (JSONObject) obj.get("BirthYear");
        try {
            return validateAndParseYear(year);
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid Birth year");
        }
    }

    private int validateAndDeathYear(JSONObject obj) {
        JSONObject year = (JSONObject) obj.get("BirthYear");
        try {
            return validateAndParseYear(year);
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid  Death year");
        }
    }

    private int validateAndParseYear(JSONObject year) {
        if (year == null) throw new InvalidDataException("Year is Null");

        try {
            return Integer.parseInt(year.toString());
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Invalid year");
        }
    }

    private Member validateAndParseMember(JSONObject object) {
        if (object == null) throw new InvalidDataException("Null member object");

        Member member = new Member();
        member.setName(validateAndParseName(object));
        member.setBirthYear(validateAndParseBirthYear(object));
        member.setDeathYear(validateAndDeathYear(object));
        member.setMembers(validateAndReturnMembers(object));
        return member;

    }


}
