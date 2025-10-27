package com.nilsson.rental.service;

import com.nilsson.rental.dao.MemberRegistry;
import com.nilsson.rental.entity.Member;

import java.util.Comparator;

public class MembershipService {
    /*• RentalService och MembershipService ska innehålla affärslogiken*/
    MemberRegistry memberRegistry;

    public MembershipService(MemberRegistry memberRegistry) {
        this.memberRegistry = memberRegistry;
    }

    public MembershipService() {
        memberRegistry = new MemberRegistry();
    }

    public MemberRegistry getMemberRegistry() {
        return memberRegistry;
    }

    public void setMemberRegistry(MemberRegistry memberRegistry) {
        this.memberRegistry = memberRegistry;
    }

    public void addMember(Member member){
        memberRegistry.addMember(member);
    }

    public void removeMember(Member member){
        memberRegistry.removeMember(member);
    }

    public Member findMember(String memberId){
        for (Member member : memberRegistry.getMemberList()){
            if(member.getId().equals(memberId)){
                return member;
            }
        }
        return null;
    }
    public void printMembers(){
        memberRegistry.printMemberList();
    }
    public void printMembers(Comparator<Member> memberComparator){
        memberRegistry.printMemberList(memberComparator);
    }

    public void changeMemberName(Member member, String newName){
        member.setName(newName);
        System.out.println("Namn ändrat till " + newName + ".");
    }

    public void changeMemberStatus(Member member, String status){
        member.setStatus(status);
    }
}
