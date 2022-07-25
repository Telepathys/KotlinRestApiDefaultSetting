package com.teamsnowball.decakill.api.model.dto.common

data class StatusDto(
    var status_code: Number ? = null,
    var auth: String ? = null,
    var description: String ? = null,
    var message: String  ? = null,
)