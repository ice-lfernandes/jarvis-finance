package br.com.jarvisfinance.application.api.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class HttpMethodLogAspect {

    /**
     * Metodo para logar a execução de métodos HTTP.
     * Para funcionar corretamente os métodos devem ser anotados com @PostMapping, @PutMapping, @PatchMapping, @DeleteMapping ou @GetMapping.
     *
     * @param joinPoint ponto de execução do método
     * @return Objeto retornado pelo método. (Boa prática que seja um ResponseEntity)
     */
    @Around("(@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
          "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
          "@annotation(org.springframework.web.bind.annotation.PatchMapping) || " +
          "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
          "@annotation(org.springframework.web.bind.annotation.GetMapping))")
    public Object logExecutionHttpMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        log.info("stage=init-{}, method={}, controller={}, parameters={}",
              joinPoint.getSignature().getName(),
              joinPoint.getSignature().getName(),
              joinPoint.getSignature().getDeclaringType().getSimpleName(),
              joinPoint.getArgs());

        // Executa o método interceptado
        Object result = joinPoint.proceed();

        long timeElapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        log.info("stage=finish-{}, method={}, controller={}, parameters={}, result={}, time-execution={}ms",
              joinPoint.getSignature().getName(),
              joinPoint.getSignature().getName(),
              joinPoint.getSignature().getDeclaringType().getSimpleName(),
              joinPoint.getArgs(),
              result,
              timeElapsed);

        return result;
    }
}
