package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.Membership
import com.team.innergrim.innergrimapi.repository.MembershipRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MembershipDomainService (private val membershipRepository: MembershipRepository) {

    // ::::: [GET] :::::
    fun getMembers (): List<Membership> {
        return membershipRepository.findAll();
    }

    fun getMemberDetail (id: Long): Optional<Membership> {
        return membershipRepository.findById(id);
    }

    // ::::: [CREATE] :::::
}