package com.team.innergrim.innergrimapi.repository

import com.team.innergrim.innergrimapi.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MemberRepository : JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {
    fun findByLoginId(loginId: String): Optional<Member>
}