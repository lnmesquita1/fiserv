package br.com.fiserv.service.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import br.com.fiserv.api.controller.EmployeeController;
import br.com.fiserv.api.controller.OrderController;
import br.com.fiserv.entity.OrderTb;
import br.com.fiserv.exception.OrderNotFoundException;
import br.com.fiserv.repository.OrderRepository;
import br.com.fiserv.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public CollectionModel<EntityModel<OrderTb>> findAll() {
		List<EntityModel<OrderTb>> orders = orderRepository.findAll().stream()
				.map(order -> toEntityModel(order, order.getId()))
				.collect(Collectors.toList());
		
		return CollectionModel.of(orders, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	
	@Override
	public OrderTb save(OrderTb order) {
		return orderRepository.save(order);
	}

	@Override
	public EntityModel<OrderTb> findOne(Long id) {
		OrderTb order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
		return toEntityModel(order, id);
	}
	
	@Override
	public EntityModel<OrderTb> update(OrderTb newOrder, Long id) {
		OrderTb updatedOrder = orderRepository.findById(id) //
				.map(order -> {
					order.setAddress(newOrder.getAddress());
					order.setCustomerName(newOrder.getCustomerName());
					return orderRepository.save(order);
				}) //
				.orElseGet(() -> {
					newOrder.setId(id);
					return orderRepository.save(newOrder);
				});

		return toEntityModel(updatedOrder, id);
	}
	
	@Override
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}
	
	private EntityModel<OrderTb> toEntityModel(OrderTb order, Long id) {
		return EntityModel.of(order,
			      linkTo(methodOn(OrderController.class).one(id)).withSelfRel(),
			      linkTo(methodOn(OrderController.class).all()).withRel("orders"));
	}

}
