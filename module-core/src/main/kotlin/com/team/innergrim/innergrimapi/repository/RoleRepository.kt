package com.team.innergrim.innergrimapi.repository

import com.team.innergrim.innergrimapi.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
}