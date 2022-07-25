package com.teamsnowball.decakill.api.component

import org.apache.commons.codec.binary.Hex
import org.springframework.stereotype.Component
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Component
class SHA512 {
    fun hash512( data: String, key: String): String {
        val sha512Hmac = Mac.getInstance("HmacSHA512")
        val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA512")
        sha512Hmac.init(secretKey)
        return Hex.encodeHexString(sha512Hmac.doFinal(data.toByteArray()))
    }
}