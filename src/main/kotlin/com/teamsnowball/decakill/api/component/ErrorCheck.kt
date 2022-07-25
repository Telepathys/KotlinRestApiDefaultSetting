package com.teamsnowball.decakill.api.component

import com.teamsnowball.decakill.api.model.dto.common.ErrorDto
import io.jsonwebtoken.MalformedJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ErrorCheck (
    @Autowired
    val logger: Logger,
) {
    fun exception(error: Throwable): ErrorDto {
        var auth = "success"
        when (error) {
            is NullPointerException -> {
                auth = "fail"
                logger.error("jwt토근을 보내지 않았습니다.")
                logger.error(error.message.toString())
            }
            is MalformedJwtException -> {
                auth = "fail"
                logger.error("jwt토근이 만료되었거나 없는 토큰입니다.")
            }
            else -> {
                logger.error(error.message.toString())
            }
        }
        return ErrorDto(
            auth
        )
    }
}