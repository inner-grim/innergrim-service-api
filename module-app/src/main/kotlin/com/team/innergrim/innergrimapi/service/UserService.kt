package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.User
import org.springframework.stereotype.Service

@Service
class UserService (private val userDomainService: UserDomainService) {

    fun getAllUsers(): List<User> {
        return userDomainService.getAllUsers();
    }

}