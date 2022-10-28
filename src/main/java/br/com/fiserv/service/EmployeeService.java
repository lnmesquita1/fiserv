package br.com.fiserv.service;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import br.com.fiserv.entity.Employee;

@Component
public interface EmployeeService {
	
	Employee save(Employee employee);
	
	EntityModel<Employee> findOne(Long id);

	CollectionModel<EntityModel<Employee>> findAll();
	
	EntityModel<Employee> update(Employee newEmployee, Long id);
	
	void deleteById(Long id);
	
}
