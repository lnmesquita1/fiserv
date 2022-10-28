package br.com.fiserv.service;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import br.com.fiserv.entity.OrderTb;

@Component
public interface OrderService {
	
	OrderTb save(OrderTb orderTb);
	
	EntityModel<OrderTb> findOne(Long id);

	CollectionModel<EntityModel<OrderTb>> findAll();
	
	EntityModel<OrderTb> update(OrderTb newOrder, Long id);
	
	void deleteById(Long id);

}
