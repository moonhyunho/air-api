package com.air.api.support.exception

import com.air.api.application.MessageSourceService
import com.air.api.interfaces.response.RestExceptionView
import com.air.api.support.MessageKey
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestExceptionHandler(
    private val messageSourceService: MessageSourceService
) : ResponseEntityExceptionHandler() {

    private val log: Logger = LoggerFactory.getLogger(RestExceptionHandler::class.java)

    @ExceptionHandler(EntityNotFoundException::class)
    fun handle(e: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        log.error(e.message, this)
        return handleExceptionInternal(
            e,
            RestExceptionView(
                HttpStatus.NOT_FOUND.value(),
                e.javaClass.simpleName,
                messageSourceService.getMessage(e.messageKey, e.objs)
            ),
            HttpHeaders.EMPTY,
            HttpStatus.NOT_FOUND,
            request
        )
    }

    @ExceptionHandler(MethodArgumentInvalidException::class)
    fun handle(e: MethodArgumentInvalidException, request: WebRequest): ResponseEntity<Any> {
        log.error(e.message, this)
        return handleExceptionInternal(
            e,
            RestExceptionView(
                HttpStatus.BAD_REQUEST.value(),
                e.messageKey.name,
                messageSourceService.getMessage(e.messageKey, e.objs)
            ),
            HttpHeaders.EMPTY,
            HttpStatus.BAD_REQUEST,
            request
        )
    }

    @ExceptionHandler(Exception::class)
    fun handle(e: Exception, request: WebRequest): ResponseEntity<Any> {
        log.error(e.message, this)
        return handleExceptionInternal(
            e,
            RestExceptionView(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                MessageKey.EXCEPTION.name,
                messageSourceService.getMessage(MessageKey.EXCEPTION)
            ),
            HttpHeaders.EMPTY,
            HttpStatus.INTERNAL_SERVER_ERROR,
            request
        )
    }
}
