package com.nilsson.rental.entity;

import java.util.Comparator;

public class MemberIdComparator implements Comparator<Member> {
    @Override
    public int compare(Member m1, Member m2) {
        return Integer.parseInt(m1.getId()) - Integer.parseInt(m2.getId());
    }
}
