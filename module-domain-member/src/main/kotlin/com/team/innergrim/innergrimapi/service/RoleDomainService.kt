package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.Role
import com.team.innergrim.innergrimapi.repository.RoleRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoleDomainService (private val roleRepository: RoleRepository) {

    // ::::: [GET] :::::
    fun getRoleList (): List<Role> {
        return roleRepository.findAll();
    }

    fun getRoleDetail (id: Long): Optional<Role> {
        return roleRepository.findById(id);
    }

    fun getRoleDetail (specification: Specification<Role>): Optional<Role> {
        return roleRepository.findOne(specification);
    }

    // ::::: [CREATE] :::::
}