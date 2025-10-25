package com.nilsson.rental.dao;

import com.nilsson.rental.entity.Member;

import java.util.Set;
import java.util.TreeSet;

public class MemberRegistry {
    /*• Inventory och MemberRegistry (hanteras i minnet via List, Map eller Set)*/
    Set<Member> memberList;

    public MemberRegistry(){
        memberList = new TreeSet<Member>();
    }

    public Set<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(Set<Member> memberList) {
        this.memberList = memberList;
    }

    public void addMember(Member member){
        memberList.add(member);
    }
}
