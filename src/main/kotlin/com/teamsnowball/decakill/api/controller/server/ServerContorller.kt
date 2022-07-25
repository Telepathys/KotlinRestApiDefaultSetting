package com.teamsnowball.decakill.api.controller.server

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Tag(name = "Server")
@Controller
class ServerController(
) {
    /* 서버 정상화 체크 */
    @Operation(
        summary = "서버 정상화 체킹",
        description = "Check Server Status"
    )
    @ResponseBody
    @GetMapping("/status")
    fun getStatus(): Int {
        return 200
    }
}