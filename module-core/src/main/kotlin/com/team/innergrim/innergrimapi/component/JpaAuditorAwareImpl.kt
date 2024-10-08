package com.team.innergrim.innergrimapi.component

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class JpaAuditorAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        // 현재 사용자 ID를 반환하는 로직 구현
        // 예를 들어, SecurityContextHolder를 사용하여 사용자 정보를 가져올 수 있습니다.
        return Optional.of("system") // 시스템 또는 현재 사용자 ID로 설정
    }
}