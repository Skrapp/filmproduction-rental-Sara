package com.nilsson.rental.dao;

import com.nilsson.rental.entity.Member;

import java.util.Comparator;
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

    public void removeMember(Member member){
        memberList.remove(member);
    }

    public void printMemberList(){
        //Default ordning på lista skrivs ut
        for(Member member : memberList){
            System.out.println(member);
        }
    }

    public void printMemberList(Comparator<Member> memberComparator){
        //Beroende på comparator skrivs listan ut
    }
}
