package br.com.jarvisfinance.domain.aspect.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class MethodLogExecutionAspect {

    /**
     * Metodo para logar a execução de métodos.
     * Para funcionar corretamente os métodos devem ser anotados com @MethodLogExecution.
     *
     * @param joinPoint ponto de execução do método
     */
    @Around("(@annotation(br.com.jarvisfinance.domain.aspect.annotation.MethodLogExecution))")
    public Object logExecutionMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        log.info("stage=init-{}, method={}, service={}, parameters={}",
              joinPoint.getSignature().getName(),
              joinPoint.getSignature().getName(),
              joinPoint.getSignature().getDeclaringType().getSimpleName(),
              joinPoint.getArgs());

        // Executa o método interceptado
        Object result = joinPoint.proceed();

        long timeElapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        log.info("stage=finish-{}, method={}, service={}, parameters={}, result={}, time-execution={}ms",
              joinPoint.getSignature().getName(),
              joinPoint.getSignature().getName(),
              joinPoint.getSignature().getDeclaringType().getSimpleName(),
              joinPoint.getArgs(),
              result,
              timeElapsed);

        return result;
    }
}
