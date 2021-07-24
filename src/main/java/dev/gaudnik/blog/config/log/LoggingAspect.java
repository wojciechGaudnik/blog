package dev.gaudnik.blog.config.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

	@Value("${blog.logging}")
	String logging;

	@Around("@annotation(Logging)")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		if (logging.equals("true")) {
			ObjectMapper mapper = new ObjectMapper();
			String methodName = joinPoint.getSignature().getName();
			String className = joinPoint.getTarget().getClass().toString();
			Object[] array = joinPoint.getArgs();
			log.info("method invoked " + className + " : " + methodName + "()" + " arguments : "
					+ mapper.writeValueAsString(array));
			Object object = joinPoint.proceed();
			log.info(className + " : " + methodName + "()" + "Response : "
					+ mapper.writeValueAsString(object));
			return object;
		} else if (logging.equals("false")) {
			return joinPoint.proceed();
		}
		throw new IllegalArgumentException("Wrong value in application.properties, blog.logging should be \"true\" or \"false\"");

	}
}
