package com.air.api.application

import com.air.api.support.MessageKey
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageSourceService(private val messageSource: MessageSource) {

    fun getMessage(messageKey: MessageKey, vararg args: Any): String {
        return getMessage(messageKey.getMessageSourceKey(), LocaleContextHolder.getLocale(), args)
    }

    private fun getMessage(messageKey: String, locale: Locale, vararg args: Any): String {
        return messageSource.getMessage(messageKey, args, locale)
    }

}