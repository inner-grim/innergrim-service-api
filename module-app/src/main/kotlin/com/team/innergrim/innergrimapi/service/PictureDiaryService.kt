package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.enums.UploadType
import com.team.innergrim.innergrimapi.web.dto.PictureDiaryRequestDto
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PictureDiaryService (
    private val pictureDiaryDomainService: PictureDiaryDomainService,
    private val s3Service: S3Service
) {

    // ::::: [GET] :::::

    // ::::: [CREATE] :::::
    @Transactional
    fun createPictureDiary(createPictureDiaryRequestDto: PictureDiaryRequestDto.Create) {

        // S3 업로드
        val imageUrl = s3Service.uploadFile(createPictureDiaryRequestDto.file, UploadType.picture_diary_image)

        // 저장
        pictureDiaryDomainService.createPictureDiary(
            createPictureDiaryRequestDto.toPictureDiaryEntity(imageUrl)
        )
    }

}