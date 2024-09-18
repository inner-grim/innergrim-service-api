package com.team.innergrim.innergrimapi.search

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.isAccessible

// TODO: Not Use 필요 없으면 삭제 예정
class Search<T : Any>(
    entityClass: T
) {

    private val propertiesMap = mutableMapOf<String, Any?>()

    init {
        // 생성자에서 entityClass의 정보를 처리
        processClassProperties(entityClass::class, entityClass)
    }

    // 클래스의 프로퍼티를 처리하여 저장
    private fun processClassProperties(kClass: KClass<*>, obj: T) {
        println("Processing class info for: ${kClass.simpleName}")

        for (prop in kClass.memberProperties) {
            prop.isAccessible = true // 비공개 프로퍼티 접근 허용
            val name = prop.name
            val value = prop.getter.call(obj)
            println("Property name: $name, value: $value")
            propertiesMap[name] = value // 프로퍼티 이름과 값을 맵에 저장
        }
    }

    // 프로퍼티를 조회하는 메서드
    fun getPropertyMap(): Map<String, Any?> {
        return propertiesMap
    }
}