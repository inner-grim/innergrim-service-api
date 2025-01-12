package com.team.innergrim.innergrimapi.external

import com.team.innergrim.innergrimapi.dto.ExternalApiRequestDto
import com.team.innergrim.innergrimapi.enums.ExternalServiceType
import com.team.innergrim.innergrimapi.service.ExternalApiService
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate

@SpringBootTest
@ActiveProfiles("local")
@DisplayName("Send Discord Test")
class DiscordServiceTests (
    private val externalApiService: ExternalApiService
): BehaviorSpec() {

    init {
        Given("Create RequestDto") {
            val endpoint = "/1287330459461750815/g7CZ68TiINkNwStUtR-xIziCsc0wOXQ_8Sn3uXdPaxNnoGj-lqv_VaeM7pg47a4Y9PmI"
            val requestDto = ExternalApiRequestDto.SendDiscordMessage(
                content = "",
                username = "InnerGrim-Notification",
                embeds = listOf(
                    ExternalApiRequestDto.SendDiscordMessage.Embed(
                        title = "Innergrim Statistics Sample",
                        description = """
                            - 기준 일자 : ${LocalDate.of(2025, 1, 12)}
                            - 회원 가입 : ${0}
                            - 회원 탈퇴 : ${0}
                            - 생성한 그림일기 ${0}
                        """.trimIndent(),
                        color = 0x00FF00 // Green color
                    )
                )
            )
            When("Send Message") {
                val response = externalApiService.post(
                    ExternalServiceType.discord,
                    endpoint,
                    requestDto
                ).block()
                Then("Check result") {
                    response shouldNotBe null
                }
            }
        }

    }
}