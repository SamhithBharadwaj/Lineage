package com.bharadwaj.samhith.lineage.service;

import com.bharadwaj.samhith.lineage.exceptions.InvalidDataException;
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

    public static FamilyLineageLoader lineageLoader;

    private FamilyLineageLoader() {

    }

    public static FamilyLineageLoader getLineageLoader() {
        if (lineageLoader == null) {
            synchronized (FamilyLineageLoader.class) {
                if (lineageLoader == null) {
                    lineageLoader = new FamilyLineageLoader();
                }
            }
        }
        return lineageLoader;
    }

    public MemberObjectValidator memberObjectValidator = new MemberObjectValidator();

    public FamilyLineage loadLineageFromFile(File file) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(file));
            return buildLineageFromJsonObj(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private FamilyLineage buildLineageFromJsonObj(JSONObject obj) {
        Object lineage = obj.get("lineage");
        if (lineage == null)
            throw new InvalidDataException("Invalid linage data.");
        return parseLineageObject((JSONObject) lineage);
    }

    private FamilyLineage parseLineageObject(JSONObject object) {
        FamilyLineage lineage = new FamilyLineage();
        lineage.setFamilyTree((object.get("FamilyTree")).toString());
        List<Member> members = new ArrayList<>();
        lineage.setMembers(memberObjectValidator.validateAndReturnMembers(object));

        return lineage;
    }

}
