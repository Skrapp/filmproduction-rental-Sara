package com.nilsson.rental.dao;

import com.nilsson.rental.entity.Member;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class MemberRegistry {
    /*• Inventory och MemberRegistry (hanteras i minnet via List, Map eller Set)*/
    Set<Member> memberSet;
    Comparator<Member> defaultComparator;

    public MemberRegistry(Comparator<Member> defaultComparator){
        memberSet = new TreeSet<>(defaultComparator);
    }

    public Comparator<Member> getDefaultComparator() {
        return defaultComparator;
    }

    public void setDefaultComparator(Comparator<Member> defaultComparator) {
        this.defaultComparator = defaultComparator;
    }

    public Set<Member> getMemberSet() {
        return memberSet;
    }

    public void setMemberSet(Set<Member> memberSet) {
        this.memberSet = memberSet;
    }

    public void addMember(Member member){
        memberSet.add(member);
    }

    public void removeMember(Member member){
        memberSet.remove(member);
    }

    //Default ordning på lista skrivs ut
    public void printMemberSet(){
        for(Member member : memberSet){
            System.out.println(member);
        }
    }

    //Skapar ny set med annan comparator och skriver ut den
    public void printMemberSet(Comparator<Member> memberComparator){
        Set<Member> sortedMemberSet = new TreeSet<>(memberComparator);
        sortedMemberSet.addAll(memberSet);

        for(Member member : sortedMemberSet){
            System.out.println(member);
        }
    }
}
