package br.com.fiserv.service;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import br.com.fiserv.dto.DeliveryScheduleDto;
import br.com.fiserv.entity.DeliverySchedule;

@Component
public interface DeliveryScheduleService {
	
	EntityModel<DeliverySchedule> save(DeliveryScheduleDto deliverySchedule);
	
	CollectionModel<EntityModel<DeliverySchedule>> findAll();
	
	EntityModel<DeliverySchedule> findOne(Long id);
	
}
