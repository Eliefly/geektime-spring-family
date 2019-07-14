package geektime.spring.springbucks.waiter.webconfig;

import geektime.spring.springbucks.waiter.controller.PerformanceInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MyWebMvcConfiguer
 *
 * @author huangfl
 * @date 19/7/14
 */
@Configuration
public class MyWebMvcConfiguer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInteceptor())
                .addPathPatterns("/coffee/**").addPathPatterns("/order/**");
    }
}
