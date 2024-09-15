package com.team.innergrim.innergrimapi.search

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

class Search {

    fun classInfo (myClass: Any) {
        var arg = myClass::class

        println("class info")
        println("isAbstract : ${arg.isAbstract}") // 클래스가 abstract로 선언되었는지 판단
        println("isCompanion :  ${arg.isCompanion}") // 클래스가 companion로 선언되었는지 판단
        println("isData : ${arg.isData}") // 클래스가 data로 선언되었는지 판단
        println("isFinal : ${arg.isFinal}") // 클래스가 final로 선언되었는지 판단
        println("isInner : ${arg.isInner}") // 클래스가 inner로 선언되었는지 판단
        println("isOpen : ${arg.isOpen}") // 클래스가 open으로 선언되었는지 판단
        println("isSealed : ${arg.isSealed}") // 클래스가 sealed로 선언되었는지 판단

        for (prop in arg.memberProperties) {
            // Get property name and value
            val name = prop.name
            val type = prop.returnType // 프로퍼티 타입 확인
            val value = prop.getter.call(myClass)
            println("Property name: $name, type: $type , value: $value")
        }
    }
}