package com.nilsson.rental.service;

import com.nilsson.rental.dao.MemberRegistry;
import com.nilsson.rental.entity.Member;

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
}
