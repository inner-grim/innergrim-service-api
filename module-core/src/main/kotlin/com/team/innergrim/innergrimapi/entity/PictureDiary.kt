package com.team.innergrim.innergrimapi.entity

import com.team.innergrim.innergrimapi.base.BaseEntity
import com.team.innergrim.innergrimapi.enums.YnCode
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "picture_diary")
class PictureDiary() : BaseEntity() {

    @Comment("감정 키워트")
    @Column(name = "emotion_keyword", nullable = false)
    lateinit var emotionKeyword: String

    @Comment("이미지 URL")
    @Column(name = "image_url", nullable = false)
    lateinit var imageUrl: String

    @Comment("일기 내용")
    @Column(name = "content", nullable = false)
    lateinit var content: String

    @Enumerated(EnumType.STRING)
    @Comment("선호 여부")
    @Column(name = "like_yn", nullable = true)
    lateinit var likeYn: YnCode

}