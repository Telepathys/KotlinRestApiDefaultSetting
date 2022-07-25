package com.teamsnowball.decakill.api.component

import org.springframework.stereotype.Component
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import org.apache.commons.codec.binary.Hex

@Component
class SHA256 {
    fun securityPassword( data: String, key: String): String {
        val sha256Hmac = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA256")
        sha256Hmac.init(secretKey)
        return Hex.encodeHexString(sha256Hmac.doFinal(data.toByteArray()))
    }
}