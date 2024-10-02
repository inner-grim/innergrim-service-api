package com.team.innergrim.innergrimapi.web

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.service.MemberService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "PictureDiary")
@RequestMapping("/picture-diary")
@RestController
class PictureDiaryController(

) {

    // ::::: [GET] :::::

    @GetMapping
    fun getPictureDiaryList() {

    }

    // ::::: [POST] :::::
    fun createPictureDiary() {

    }

}