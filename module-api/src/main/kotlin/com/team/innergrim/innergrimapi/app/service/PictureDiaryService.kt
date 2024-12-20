package com.team.innergrim.innergrimapi.app.service

import com.team.innergrim.innergrimapi.app.web.dto.PictureDiaryRequestDto
import com.team.innergrim.innergrimapi.entity.PictureDiary
import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.enums.UploadType
import com.team.innergrim.innergrimapi.exception.BusinessException
import com.team.innergrim.innergrimapi.service.PictureDiaryDomainService
import com.team.innergrim.innergrimapi.service.S3Service
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PictureDiaryService (
    private val pictureDiaryDomainService: PictureDiaryDomainService,
    private val s3Service: S3Service
) {

    // ::::: [GET] :::::
    fun getPictureDiaries()/*: List<PictureDiary>*/ {
//        return pictureDiaryDomainService.getPictureDiaries()
//        return List
    }

    fun getPictureDiaryDetail(id: Long) : PictureDiary {
        return pictureDiaryDomainService.getPictureDiaryDetail(id).orElseThrow {
            BusinessException(ErrorCode.NOT_FOUND, "picture diary")
        }
    }

    // ::::: [CREATE] :::::
    @Transactional
    fun createPictureDiary(createPictureDiaryRequestDto: PictureDiaryRequestDto.CreatePictureDiary) {

        // S3 업로드
        val imageUrl = s3Service.uploadFile(createPictureDiaryRequestDto.file, UploadType.picture_diary_image)

        try {
            // 저장
            pictureDiaryDomainService.createPictureDiary(
                createPictureDiaryRequestDto.toPictureDiaryEntity(imageUrl)
            )
        } catch (e:Exception) {
            throw BusinessException(ErrorCode.CREATE_FAIL, "pictureDiary")
        }
    }

}