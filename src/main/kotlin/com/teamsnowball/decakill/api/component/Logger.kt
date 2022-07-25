package com.teamsnowball.decakill.api.component

import mu.KLogger
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Logger (
    @Autowired
    private val logger: KLogger = KotlinLogging.logger {}
) {
    /* logger trace */
    fun trace(text: String) {
        return logger.trace(text)
    }
    /* logger debug */
    fun debug(text: String) {
        return logger.debug(text)
    }
    /* logger info */
    fun info(text: String) {
        return logger.info(text)
    }
    /* logger warn */
    fun warn(text: String) {
        return logger.warn(text)
    }
    /* logger error */
    fun error(text: String) {
        return logger.error(text)
    }
}