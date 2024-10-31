package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.PictureDiary
import com.team.innergrim.innergrimapi.repository.PictureDiaryRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*


@Service
class PictureDiaryDomainService (
    private val pictureDiaryRepository: PictureDiaryRepository
) {

    // ::::: [GET] :::::
    fun getPictureDiaries (specification: Specification<PictureDiary>) : List<PictureDiary> {
        return pictureDiaryRepository.findAll(specification);
    }

    fun getPictureDiaryDetail (id: Long) : Optional<PictureDiary> {
        return pictureDiaryRepository.findById(id)
    }

    // ::::: [CREATE] :::::
    fun createPictureDiary (pictureDiary: PictureDiary) {
        pictureDiaryRepository.save(pictureDiary)
    }
}