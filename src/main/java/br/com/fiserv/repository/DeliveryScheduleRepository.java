package br.com.fiserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiserv.entity.DeliverySchedule;

public interface DeliveryScheduleRepository extends JpaRepository<DeliverySchedule, Long> {

}
