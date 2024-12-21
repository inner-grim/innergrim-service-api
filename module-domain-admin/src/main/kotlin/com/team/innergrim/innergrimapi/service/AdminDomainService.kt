package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.Admin
import com.team.innergrim.innergrimapi.repository.AdminRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*

@Service
class AdminDomainService (
    private val adminRepository: AdminRepository
) {

    // ::::: [GET] :::::

    fun getAdmins(): List<Admin> {
        return adminRepository.findAll()
    }

    fun getAdminDetail(id: Long): Optional<Admin> {
        return adminRepository.findById(id)
    }

    fun getAdminDetail(specification: Specification<Admin>): Optional<Admin> {
        return adminRepository.findOne(specification)
    }

}