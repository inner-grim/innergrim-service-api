package com.team.innergrim.innergrimapi.entity

import com.team.innergrim.innergrimapi.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "picture_diary")
class PictureDiary() : BaseEntity() {

    @Comment("생성 일자")
    @Column(name = "create_date", nullable = false)
    lateinit var createDate: LocalDate

    @Comment("감정 키워트")
    @Column(name = "emotion_keyword", nullable = false)
    lateinit var emotionKeyword: String

    @Comment("이미지 URL")
    @Column(name = "image_url", nullable = false)
    lateinit var imageUrl: String

    @Comment("일기 내용")
    @Column(name = "content", nullable = false)
    lateinit var content: String

    @Comment("일기 내용")
    @Column(name = "like_yn", nullable = true)
    lateinit var likeYn: String

}