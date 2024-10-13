package com.team.innergrim.innergrimapi.repository

import com.team.innergrim.innergrimapi.entity.Membership
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface MembershipRepository : JpaRepository<Membership, Long>, JpaSpecificationExecutor<Membership> {
}