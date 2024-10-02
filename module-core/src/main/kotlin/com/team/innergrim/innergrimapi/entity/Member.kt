package com.team.innergrim.innergrimapi.entity

import com.team.innergrim.innergrimapi.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "member")
class Member() : BaseEntity() {

    @Comment("이름")
    @Column(name = "name", nullable = false, length = 255)
    lateinit var name: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id", nullable = false)
    lateinit var membership: Membership

    // name을 인자로 받는 부 생성자
    constructor(name: String, createdAt : LocalDateTime) : this() {
        this.name = name
        this.createdAt = createdAt
    }
}