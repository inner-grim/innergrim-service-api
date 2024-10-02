package com.team.innergrim.innergrimapi.entity

import com.team.innergrim.innergrimapi.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "membership")
class Membership() : BaseEntity() {

    @Comment("이름")
    @Column(name = "name", nullable = false, length = 255)
    lateinit var name: String

}