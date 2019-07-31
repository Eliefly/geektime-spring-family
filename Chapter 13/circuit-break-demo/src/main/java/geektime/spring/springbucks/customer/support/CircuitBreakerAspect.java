package geektime.spring.springbucks.customer.support;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
@Slf4j
public class CircuitBreakerAspect {
    private static final Integer THRESHOLD = 3;

    /**
     * 调用失败的次数
     */
    private Map<String, AtomicInteger> counter = new ConcurrentHashMap<>();

    /**
     * 熔断保护的次数
     */
    private Map<String, AtomicInteger> breakCounter = new ConcurrentHashMap<>();

    /**
     * 切面实现的熔断。实现的效果是，连续调用3次失败后，进行熔断处理直接返回null，连续熔断3次后会再次调用，如果成功归零计数。
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* geektime.spring.springbucks.customer.integration..*(..))")
    public Object doWithCircuitBreaker(ProceedingJoinPoint pjp) throws Throwable {
        // 获取方法的 signature
        String signature = pjp.getSignature().toLongString();
        log.info("Invoke {}", signature);
        Object retVal;
        try {
            // 判断counter是否包含 signature
            if (counter.containsKey(signature)) {
                // 判断是否达到条件，失败次数大于阈值，熔断保护小于阈值，此时才进行熔断
                if (counter.get(signature).get() > THRESHOLD &&
                        breakCounter.get(signature).get() < THRESHOLD) {
                    // 熔断处理，返回null， breakCounter + 1
                    log.warn("Circuit breaker return null, break {} times.",
                            breakCounter.get(signature).incrementAndGet());
                    return null;
                }
            } else {
                // 初始化 counter， breakCounter
                counter.put(signature, new AtomicInteger(0));
                breakCounter.put(signature, new AtomicInteger(0));
            }
            retVal = pjp.proceed();
            // 如果正常处理了，归零 counter， breakCounter
            counter.get(signature).set(0);
            breakCounter.get(signature).set(0);
        } catch (Throwable t) {
            // 处理异常，归零 counter + 1，归零 breakCounter
            log.warn("Circuit breaker counter: {}, Throwable {}",
                    counter.get(signature).incrementAndGet(), t.getMessage());
            breakCounter.get(signature).set(0);
            throw t;
        }
        return retVal;
    }
}
