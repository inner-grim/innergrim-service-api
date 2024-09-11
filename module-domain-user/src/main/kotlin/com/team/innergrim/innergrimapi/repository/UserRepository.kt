package com.team.innergrim.innergrimapi.repository

import com.team.innergrim.innergrimapi.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
}