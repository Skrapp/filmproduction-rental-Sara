package com.nilsson.rental.service;

import com.nilsson.rental.dao.MemberRegistry;
import com.nilsson.rental.entity.Member;
import com.nilsson.rental.entity.MemberIdComparator;
import com.nilsson.rental.entity.pricepolicy.PricePolicy;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MembershipService {
    /*• MembershipService ska innehålla affärslogiken*/
    MemberRegistry memberRegistry;

    public MembershipService(MemberRegistry memberRegistry) {
        this.memberRegistry = memberRegistry;
    }

    public MembershipService() {
        memberRegistry = new MemberRegistry(new MemberIdComparator());
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

    public Member findMemberById(String memberId) throws NullPointerException {
        for (Member member : memberRegistry.getMemberSet()){
            if(member.getId().equals(memberId)){
                return member;
            }
        }
        throw new NullPointerException("Det finns ingen medlem med det id:et");
    }

    public void changeMemberName(Member member, String newName){
        member.setName(newName);
    }

    public void changeMemberLevel(Member member, String level){
        member.setStatus(level);
    }

    ///Skapar en stream som filtrerar members enligt om member(m) level-klass är av samma klass som pricepolicyfilter-klassen
    /// samt filtrerar på sökord i namn
    /// och samlar sedan den som en TreeSet som sorteras med memberComparator
    /// Skriver ut alla members
    public void printMembers(Comparator<Member> memberComparator, Class<? extends PricePolicy> pricePolicyFilter, String searchName){
        Set<Member> sortedMemberSet = memberRegistry.getMemberSet().stream()
                .filter(m->pricePolicyFilter.isInstance(m.getLevel()))
                .filter(m->m.getName().toLowerCase().contains(searchName.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>(memberComparator)));

        for(Member member : sortedMemberSet){
            System.out.println(member);
        }
    }
}
