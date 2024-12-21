package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.enums.ErrorCode
import com.team.innergrim.innergrimapi.enums.UploadType
import com.team.innergrim.innergrimapi.exception.BusinessException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class S3Service(
    private val s3Client: S3Client,
    @Value("s3.image-url") private val imageUrl: String,
    @Value("s3.bucket") private val bucket: String
) {

    fun uploadFile(multipartFile: MultipartFile, uploadType: UploadType): String {

        val originalFileName = multipartFile.originalFilename ?: ""
        val extension = originalFileName.substringAfterLast('.', "")

        val fileName = UUID.randomUUID().toString()
        val uploadDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)

        try {
            val putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .contentType(multipartFile.getContentType())
                .contentLength(multipartFile.getSize())
                .key("${uploadType.name}/${uploadDate}/${fileName}.${extension}")
                .build()
            val requestBody = RequestBody.fromBytes(multipartFile.getBytes())

            s3Client.putObject(putObjectRequest, requestBody)
        } catch (e: Exception) {
            e.printStackTrace()
            throw BusinessException(ErrorCode.FILE_UPLOAD_FAIL, uploadType.name)
        }
        return "${imageUrl}/${uploadType.name}/${uploadDate}/${fileName}.${extension}"
    }

}