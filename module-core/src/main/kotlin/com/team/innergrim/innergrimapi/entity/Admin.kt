//package com.team.innergrim.innergrimapi.entity
//
//import com.team.innergrim.innergrimapi.base.BaseEntity
//import jakarta.persistence.*
//import org.hibernate.annotations.Comment
//
//@Entity
//@Table(name = "admin")
//class Admin() : BaseEntity() {
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id", nullable = false)
//    lateinit var role: Role
//
//    @Comment("로그인 시 아이디")
//    @Column(name = "login_id", nullable = false, length = 50)
//    lateinit var loginId: String
//
//    @Comment("로그인 시 비밀번호")
//    @Column(name = "password", nullable = false, unique = true, length = 255)
//    lateinit var password: String
//
//    @Comment("이름")
//    @Column(name = "name", nullable = false, length = 50)
//    var name: String? = null
//
//    @Comment("이메일")
//    @Column(name = "email", nullable = true, length = 255)
//    var email: String? = null
//
//    @Comment("생년월일")
//    @Column(name = "birth_date", nullable = true, length = 8)
//    var birthDate: String? = null
//
//    @Comment("전화번호")
//    @Column(name = "phone_number", nullable = false, length = 20)
//    var phoneNumber: String? = null
//
//}