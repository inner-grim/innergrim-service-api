package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.PictureDiary
import com.team.innergrim.innergrimapi.repository.PictureDiaryRepository
import org.springframework.stereotype.Service


@Service
class PictureDiaryDomainService (
    private val pictureDiaryRepository: PictureDiaryRepository
) {

    // ::::: [GET] :::::

    // ::::: [CREATE] :::::
    fun createPictureDiary (pictureDiary: PictureDiary) {
        pictureDiaryRepository.save(pictureDiary)
    }
}