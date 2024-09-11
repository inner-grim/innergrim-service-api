package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.User
import com.team.innergrim.innergrimapi.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserDomainService (private val userRepository: UserRepository) {

    fun getAllUsers (): List<User> {
        return userRepository.findAll();
    }

}