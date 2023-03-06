package br.com.alura.anyflix.log

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

const val BASE_PACKAGE = "br.com.alura.anyflix"

@Aspect
@Component
@Configuration
class ApplicationLogger {

    private val logger: Logger = LoggerFactory.getLogger(
        ApplicationLogger::class.java
    )

    @Pointcut("execution(* $BASE_PACKAGE..services..*(..))")
    fun services() {
    }

    @Pointcut("execution(* $BASE_PACKAGE..controllers..*(..))")
    fun controllers() {
    }

    @Around("services() || controllers()")
    @Throws(Throwable::class)
    fun profile(joinPoint: ProceedingJoinPoint): Any? {
        val targetClass = joinPoint.signature.declaringTypeName
        val targetMethod = joinPoint.signature.name
        val targetParameters = joinPoint.args
        return try {
            joinPoint.proceed()
        } catch (e: IllegalStateException) {
            // avoid throw exception unnecessarily
        } finally {
            val log = buildMessageLog(
                targetClass,
                targetMethod, targetParameters
            )
            logger.info(log)
        }
    }

    private fun buildMessageLog(
        className: String?,
        methodName: String?,
        parameters: Array<Any>
    ): String {
        val classWithMethodName = "$className - $methodName"
        if (parameters.isNotEmpty()) {
            return classWithMethodName + " args: ${parameters.toList()}"
        }
        return classWithMethodName
    }

}