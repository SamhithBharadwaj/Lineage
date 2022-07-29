package com.bharadwaj.samhith.lineage.processes.output;

import com.bharadwaj.samhith.lineage.models.Member;
import com.bharadwaj.samhith.lineage.processes.MembersAgeIndex;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class AgeIndexOutputWriter {
    private MembersAgeIndex ageIndex;
    private Writer writer;

    public AgeIndexOutputWriter(MembersAgeIndex ageIndex, Writer writer) {
        this.ageIndex = ageIndex;
        this.writer = writer;
    }

    public void write() throws IOException {
        writeAllMembersAndAge();
        writeAllMembersAcendingByAge();
        writePeriodActive();
        writeMeanAge();
        writeMedianAge();
        writeIQR();
        writeLongestAndYougest();
    }

    private void writeAllMembersAndAge() throws IOException {
        writer.append("Members and age \n");
        writeNameAndAgeForList(ageIndex.getMembers());
        writer.append("\n");
    }

    private void writeAllMembersAcendingByAge() throws IOException {
        writer.append("Members sorted by age \n");
        writeNameAndAgeForList(ageIndex.getSortedByAge());
        writer.append("\n");
    }

    private void writePeriodActive() throws IOException {
        writer.append("Period active : " + ageIndex.getFirstBirth() + " : " + ageIndex.getLastDeath() + "\n");
    }

    private void writeMeanAge() throws IOException {
        writer.append("Mean age : " + ageIndex.getMean() + "\n");
    }

    private void writeMedianAge() throws IOException {
        writer.append("Median age : " + ageIndex.getMedianAge() + "\n");
    }

    private void writeIQR() throws IOException {
        writer.append("Members sorted by age \n");
        writeNameAndAgeForList(ageIndex.getIQR());
        writer.append("\n");
    }

    private void writeNameAndAgeForList(List<Member> members) throws IOException {
        for (Member member : members) {
            writer.write(member.getName() + " " + member.getAge() + "\n");
        }
    }

    private void writeLongestAndYougest() throws IOException {
        writer.append("Longest lived : " + ageIndex.getLivedLongest().getName() + " age :" + ageIndex.getLivedLongest().getAge() + "\n");
        writer.append("Shortest lived : " + ageIndex.getShortestLived().getName() + " age :" + ageIndex.getShortestLived().getAge() + "\n");
    }


}
