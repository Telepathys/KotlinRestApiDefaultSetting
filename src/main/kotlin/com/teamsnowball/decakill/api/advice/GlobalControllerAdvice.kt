package com.teamsnowball.decakill.api.advice

import com.teamsnowball.decakill.api.component.Logger
import com.teamsnowball.decakill.api.model.dto.common.StatusDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
@ResponseBody
class GlobalControllerAdvice(
    @Autowired
    val logger: Logger,
) {
    @ExceptionHandler(
        HttpMessageNotReadableException::class,
    )
    fun httpMessageNotReadableException(
        e: HttpMessageNotReadableException
    ): StatusDto {
        val status = StatusDto(
            400,
            null,
            "Error",
            "Json 형식 오류"
        )
        logger.error("Json 형식 오류")
        return status
    }
}