package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.Role
import com.team.innergrim.innergrimapi.repository.RoleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoleDomainService (private val membershipRepository: RoleRepository) {

    // ::::: [GET] :::::
    fun getMembers (): List<Role> {
        return membershipRepository.findAll();
    }

    fun getMemberDetail (id: Long): Optional<Role> {
        return membershipRepository.findById(id);
    }

    // ::::: [CREATE] :::::
}