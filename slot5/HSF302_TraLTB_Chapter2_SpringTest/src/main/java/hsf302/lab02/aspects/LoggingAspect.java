package hsf302.lab02.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* hsf302.lab02.services.*.*(..))")
    public void logBefore(JoinPoint jp) {
        System.out.println("[BEFORE] " + jp.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "execution(* hsf302.lab02.services.*.*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint jp, Object result) {
        System.out.println("[AFTER] " + jp.getSignature().getName() + " returned: " + result);
    }

    @Around("execution(* hsf302.lab02.services.*.*(..))")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[ELAPSED] " + pjp.getSignature().getName() + " took " + elapsed + "ms");
        return result;
    }
}
