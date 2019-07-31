package geektime.spring.springbucks.customer.integration;

import geektime.spring.springbucks.customer.model.Coffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class FallbackCoffeeService implements CoffeeService {

    private static final Logger log = LoggerFactory.getLogger(FallbackCoffeeService.class);

    @Override
    public List<Coffee> getAll() {
        log.warn("Fallback to EMPTY menu.");
        return Collections.emptyList();
    }

    @Override
    public Coffee getById(Long id) {
        return null;
    }

    @Override
    public Coffee getByName(String name) {
        return null;
    }
}
