package com.bharadwaj.samhith.lineage.processes;

import com.bharadwaj.samhith.lineage.models.FamilyLineage;
import com.bharadwaj.samhith.lineage.models.Member;

import java.io.Writer;
import java.util.Collection;
import java.util.Collections;

public class DataCollector {
    private LineIndex lineIndex = new LineIndex();
    private MembersAgeIndex ageIndex = new MembersAgeIndex();

    public void iterateFamilyLineage(FamilyLineage lineage) {
        for (Member member : lineage.getMembers()) {
            iterateAndAnalyzeMember(member, lineage.getFamilyTree(), 0);
        }

    }

    private void iterateAndAnalyzeMember(Member member, String lineSoFar, int count) {
        ageIndex.addMember(member);
        processLineIndexForMemberLine(member, lineSoFar, count);

    }

    private void processLineIndexForMemberLine(Member member, String lineSoFar, int count) {
        lineSoFar=lineSoFar.concat(" -> ");
        lineSoFar=lineSoFar.concat(member.getName());
        count++;
        if (member.getMembers() != null && member.getMembers().size() > 0) {
            for (Member subMembers : member.getMembers()) {
                iterateAndAnalyzeMember(subMembers, lineSoFar, count);
            }
        } else {
            lineIndex.addLine(lineSoFar.toString(), count);
        }
    }

    public LineIndex getLineIndex() {
        return lineIndex;
    }

    public MembersAgeIndex getAgeIndex() {
        return ageIndex;
    }
}
