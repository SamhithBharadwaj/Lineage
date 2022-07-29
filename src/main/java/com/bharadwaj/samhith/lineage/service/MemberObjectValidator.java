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
        Object name = obj.get("Name");
        if (name == null) throw new InvalidDataException("Name is null");
        String nameStr = name.toString();
        return nameStr;

    }

    private int validateAndParseBirthYear(JSONObject obj) {
        try {
            return validateAndParseYear(obj.get("BirthYear"));
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid Birth year");
        }
    }

    private int validateAndDeathYear(JSONObject obj) {
        try {
            return validateAndParseYear(obj.get("DeathYear"));
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid  Death year");
        }
    }

    private int validateAndParseYear(Object year) {
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
        validateMemberAge(member);
        member.setMembers(validateAndReturnMembers(object));
        return member;

    }

    private void validateMemberAge(Member m){
        if(m.getAge()<0)
            throw new InvalidDataException("Death year before birth Year");
    }


}
