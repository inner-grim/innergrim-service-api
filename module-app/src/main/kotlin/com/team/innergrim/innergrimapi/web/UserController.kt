package com.team.innergrim.innergrimapi.web

import com.team.innergrim.innergrimapi.entity.User
import com.team.innergrim.innergrimapi.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(private val userService: UserService) {

    // ::::: [GET] :::::

    @GetMapping
    fun getAllUsers(): List<User> {
        return userService.getAllUsers();
    }

}