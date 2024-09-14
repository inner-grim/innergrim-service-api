package com.team.innergrim.innergrimapi.enums

enum class MemberStatus(
    val desc: String
) {
    regist("가입"),     // 가입 상태
    suspended("정지"),  // 정지 상태
    dormant("휴면"),    // 휴면 상태
    withdraw("탈퇴")    // 탈퇴 상태
}