package br.com.fiserv.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiserv.entity.OrderTb;
import br.com.fiserv.service.OrderService;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/list")
	public CollectionModel<EntityModel<OrderTb>> all() {
		return orderService.findAll();
	}
	
	@PostMapping("/create")
	public ResponseEntity<EntityModel<OrderTb>> newOrder(@RequestBody OrderTb newOrder) {
		OrderTb order = orderService.save(newOrder);
		return ResponseEntity //
		        .created(linkTo(methodOn(EmployeeController.class).one(order.getId())).toUri()) //
		        .body(EntityModel.of(order));
	}
	
	@GetMapping("/find/{id}")
	public EntityModel<OrderTb> one(@PathVariable Long id) {
		return orderService.findOne(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody OrderTb newOrder, @PathVariable Long id) {
		EntityModel<OrderTb> updatedOrder = orderService.update(newOrder, id);

		return ResponseEntity //
				.created(linkTo(methodOn(EmployeeController.class).one(id)).toUri()) //
				.body(updatedOrder);
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> deleteOrder(@PathVariable Long id) {
		orderService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
