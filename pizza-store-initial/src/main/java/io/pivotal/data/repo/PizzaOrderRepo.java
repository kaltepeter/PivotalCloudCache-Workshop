package io.pivotal.data.repo;

import io.pivotal.data.domain.PizzaOrder;
import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.gemfire.repository.query.annotation.Hint;
import org.springframework.data.gemfire.repository.query.annotation.Limit;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "pizzaOrders")
public interface PizzaOrderRepo extends GemfireRepository<PizzaOrder, String> {
    @Limit(10)
    @Hint("CustomerEmailIndex")
    @Query("SELECT * FROM /pizza_orders_kayla o WHERE o.customerInfo.email = $1" )
    List<PizzaOrder> findPizzaOrderByEmailId(String emailId);
}