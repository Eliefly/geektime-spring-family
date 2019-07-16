package geektime.spring.springbucks.waiter.repository;

import geektime.spring.springbucks.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "/order") // 不加默认的path是 coffeeOrder
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
