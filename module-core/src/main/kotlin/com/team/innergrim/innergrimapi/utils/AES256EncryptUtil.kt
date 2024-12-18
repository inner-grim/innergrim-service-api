package com.team.innergrim.innergrimapi.utils

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AES256EncryptUtil {

    private const val SECRET_KEY = "01234567890123456789012345678901" // 32-byte 키
    private const val IV = "0123456789012345" // 16-byte IV

    private fun getSecretKeySpec(): SecretKeySpec {
        return SecretKeySpec(SECRET_KEY.toByteArray(), "AES")
    }

    private fun getIvParameterSpec(): IvParameterSpec {
        return IvParameterSpec(IV.toByteArray())
    }

    fun encrypt(data: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(), getIvParameterSpec())
        val encryptedBytes = cipher.doFinal(data.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

}