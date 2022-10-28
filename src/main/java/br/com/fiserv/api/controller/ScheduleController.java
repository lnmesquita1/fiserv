package br.com.fiserv.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiserv.dto.DeliveryScheduleDto;
import br.com.fiserv.entity.DeliverySchedule;
import br.com.fiserv.service.DeliveryScheduleService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
	
	@Autowired
	DeliveryScheduleService deliveryScheduleService;
	
	@GetMapping("/list")
	public CollectionModel<EntityModel<DeliverySchedule>> all() {
		return deliveryScheduleService.findAll();
	}
	
	@GetMapping("/find/{id}")
	public EntityModel<DeliverySchedule> one(@PathVariable Long id) {
		return deliveryScheduleService.findOne(id);
	}
	
	@PostMapping("/create")
	public EntityModel<DeliverySchedule> newSchedule(@RequestBody DeliveryScheduleDto newSchedule) {
		return deliveryScheduleService.save(newSchedule);
	}

}
