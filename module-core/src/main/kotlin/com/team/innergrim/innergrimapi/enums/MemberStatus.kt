package com.team.innergrim.innergrimapi.enums

enum class MemberStatus(
    val desc: String
) {
    normal("일반"),     // 가입 상태
    suspended("정지"),  // 정지 상태
    dormant("휴면"),    // 휴면 상태
    withdraw("탈퇴"),    // 탈퇴 상태
    penalty_withdraw("강제 탈퇴")    // 강제 탈퇴 상태
}