package com.team.innergrim.innergrimapi.entity

import com.team.innergrim.innergrimapi.base.BaseEntity
import com.team.innergrim.innergrimapi.enums.RoleType
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "role")
class Role() : BaseEntity() {

    @Enumerated(EnumType.STRING)
    @Comment("역할 타입")
    @Column(name = "role_type", nullable = false, length = 30)
    lateinit var roleType: RoleType

}