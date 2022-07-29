package com.bharadwaj.samhith.lineage.processes;

import com.bharadwaj.samhith.lineage.models.Member;

import java.util.*;

public class MembersAgeIndex {
    private List<Member> members = new ArrayList<>();
    private float mean = 0;
    private int size = 0;

    private int firstBirth = Integer.MAX_VALUE;
    private int lastDeath = Integer.MIN_VALUE;

    private boolean sorted = false;

    public void addMember(Member member) {
        if (member == null) {
            return;
        }
        if (sorted) {
            throw new RuntimeException("Index immutable");
        }
        members.add(member);
        mean = ((mean * size) + member.getAge()) / (size + 1);
        size++;
        firstBirth = Math.min(member.getBirthYear(), firstBirth);
        lastDeath = Math.max(member.getDeathYear(), lastDeath);
    }

    private void sort() {
        Collections.sort(members, Comparator.comparing(Member::getAge));
        sorted = true;
        return;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Member> getSortedByAge() {
        if (!sorted) {
            sort();
        }
        return members;
    }

    public int getMedianAge() {
        if (!sorted) {
            sort();
        }
        return members.get(size / 2).getAge();
    }

    public float getMean() {
        return mean;
    }

    public List<Member> getIQR() {
        if (!sorted) {
            sort();
        }
        return members.subList(size / 4, 3 * size / 4);
    }

    public Member getLivedLongest() {
        if (!sorted) {
            sort();
        }
        return members.get(size - 1);
    }

    public Member getShortestLived() {
        if (!sorted) {
            sort();
        }
        return members.get(0);
    }

    public int getFirstBirth() {
        return firstBirth;
    }

    public int getLastDeath() {
        return lastDeath;
    }
}
