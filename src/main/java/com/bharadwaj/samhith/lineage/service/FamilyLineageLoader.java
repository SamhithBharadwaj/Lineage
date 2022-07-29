package com.bharadwaj.samhith.lineage.service;

import com.bharadwaj.samhith.lineage.models.FamilyLineage;
import com.bharadwaj.samhith.lineage.models.Member;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FamilyLineageLoader {

    public MemberObjectValidator memberObjectValidator = new MemberObjectValidator();

    public List<FamilyLineage> loadLineagesFromFile(File file) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(file));
            return buildLineagesFromJsonObj(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private List<FamilyLineage> buildLineagesFromJsonObj(JSONObject obj) {
        List<FamilyLineage> familyLineages = new ArrayList<>();
        for (Object lineageObject : (JSONArray) obj.get("lineage")) {
            familyLineages.add(parseLineageObject((JSONObject) lineageObject));
        }
        return familyLineages;
    }

    private FamilyLineage parseLineageObject(JSONObject object) {
        FamilyLineage lineage = new FamilyLineage();
        lineage.setFamilyTree((object.get("FamilyTree")).toString());
        List<Member> members = new ArrayList<>();
        lineage.setMembers(memberObjectValidator.validateAndReturnMembers(object));

        return lineage;
    }

}
