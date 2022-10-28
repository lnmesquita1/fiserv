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
import br.com.fiserv.api.controller.ScheduleController;
import br.com.fiserv.dto.DeliveryScheduleDto;
import br.com.fiserv.entity.DeliverySchedule;
import br.com.fiserv.entity.Employee;
import br.com.fiserv.entity.OrderTb;
import br.com.fiserv.exception.EmployeeNotFoundException;
import br.com.fiserv.exception.OrderNotFoundException;
import br.com.fiserv.repository.DeliveryScheduleRepository;
import br.com.fiserv.repository.EmployeeRepository;
import br.com.fiserv.repository.OrderRepository;
import br.com.fiserv.service.DeliveryScheduleService;

@Service
public class DeliveryScheduleServiceImpl implements DeliveryScheduleService {
	
	@Autowired
	DeliveryScheduleRepository deliveryScheduleRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public EntityModel<DeliverySchedule> save(DeliveryScheduleDto deliveryScheduleDto) {
		OrderTb order = orderRepository.findById(deliveryScheduleDto.getOrderId()).orElseThrow(() -> new OrderNotFoundException(deliveryScheduleDto.getOrderId()));
		Employee employee = employeeRepository.findById(deliveryScheduleDto.getEmpoyeeId()).orElseThrow(() -> new EmployeeNotFoundException(deliveryScheduleDto.getEmpoyeeId()));
		DeliverySchedule deliverySchedule = new DeliverySchedule();
		deliverySchedule.setEmployee(employee);
		deliverySchedule.setOrder(order);
		deliverySchedule.setDeliveryDate(deliveryScheduleDto.getDeliveryDate());
		
		deliverySchedule = deliveryScheduleRepository.save(deliverySchedule);
		return toEntityModel(deliverySchedule, deliverySchedule.getId());
	}
	
	@Override
	public CollectionModel<EntityModel<DeliverySchedule>> findAll() {
		List<EntityModel<DeliverySchedule>> deliverySchedules = deliveryScheduleRepository.findAll().stream()
				.map(deliverySchedule -> toEntityModel(deliverySchedule, deliverySchedule.getId()))
				.collect(Collectors.toList());
		
		return CollectionModel.of(deliverySchedules, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	
	@Override
	public EntityModel<DeliverySchedule> findOne(Long id) {
		DeliverySchedule deliverySchedule = deliveryScheduleRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		return toEntityModel(deliverySchedule, id);
	}
	
	private EntityModel<DeliverySchedule> toEntityModel(DeliverySchedule deliverySchedule, Long id) {
		EntityModel<DeliverySchedule> em = EntityModel.of(deliverySchedule,
			      linkTo(methodOn(ScheduleController.class).one(id)).withSelfRel(),
			      linkTo(methodOn(ScheduleController.class).all()).withRel("schedules"));
		
		return em;
	}
	

}
