package com.team.innergrim.innergrimapi.entity

import com.team.innergrim.innergrimapi.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Entity
@Table(name = "role")
class Role() : BaseEntity() {

    @Comment("이름")
    @Column(name = "name", nullable = false, length = 255)
    lateinit var name: String

}