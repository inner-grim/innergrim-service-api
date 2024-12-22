//package com.team.innergrim.innergrimapi.specification
//
//import com.team.innergrim.innergrimapi.dto.SearchAdminDto
//import com.team.innergrim.innergrimapi.entity.Admin
//import org.springframework.data.jpa.domain.Specification
//
//object AdminSpecification {
//
//    fun setSpecification (searchDto: SearchAdminDto) : Specification<Admin> {
//
//        var specification: Specification<Admin> = Specification.where(null)
//
//        // ID
//        if (searchDto.id != null) {
//            specification = specification.and(Specification { root, query, criteriaBuilder ->
//                criteriaBuilder.equal(root.get<Long>("id"), searchDto.id)
//            })
//        }
//
//        // SocialId
//        if (!searchDto.socialId.isNullOrEmpty()) {
//            specification = specification.and(Specification { root, query, criteriaBuilder ->
//                criteriaBuilder.equal(root.get<String>("socialId"), searchDto.socialId)
//            })
//        }
//
//        // 이름
//        if (!searchDto.name.isNullOrEmpty()) {
//            specification = specification.and(Specification { root, query, criteriaBuilder ->
//                criteriaBuilder.equal(root.get<String>("name"), searchDto.specification)
//            })
//        }
//
//        return specification
//    }
//
//}