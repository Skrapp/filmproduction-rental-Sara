package com.nilsson.rental.dao;

import com.nilsson.rental.entity.Member;

import java.util.Set;
import java.util.TreeSet;

public class MemberRegistry {
    /*• Inventory och MemberRegistry (hanteras i minnet via List, Map eller Set)*/
    Set<Member> memberRegistry;
    //TODO är treeset rätt eller ska det bara 

    public MemberRegistry(){
        memberRegistry = new TreeSet<>();
    }

    public Set<Member> getMemberRegistry() {
        return memberRegistry;
    }

    public void setMemberRegistry(Set<Member> memberRegistry) {
        this.memberRegistry = memberRegistry;
    }

    public void addMember(Member member){
        memberRegistry.add(member);
    }
}
