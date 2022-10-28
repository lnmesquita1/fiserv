package br.com.fiserv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import br.com.fiserv.api.controller.EmployeeController;
import br.com.fiserv.entity.Employee;
import br.com.fiserv.repository.EmployeeRepository;
import br.com.fiserv.service.EmployeeService;
import br.com.fiserv.exception.EmployeeNotFoundException;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public CollectionModel<EntityModel<Employee>> findAll() {
		List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
				.map(employee -> toEntityModel(employee, employee.getId()))
				.collect(Collectors.toList());
		
		return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	
	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public EntityModel<Employee> findOne(Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		return toEntityModel(employee, id);
	}
	
	@Override
	public EntityModel<Employee> update(Employee newEmployee, Long id) {
		Employee updatedEmployee = employeeRepository.findById(id) //
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setAdmissionDate(newEmployee.getAdmissionDate());
					employee.setWorkingPeriod(newEmployee.getWorkingPeriod());
					return employeeRepository.save(employee);
				}) //
				.orElseGet(() -> {
					newEmployee.setId(id);
					return employeeRepository.save(newEmployee);
				});

		return toEntityModel(updatedEmployee, id);
	}
	
	@Override
	public void deleteById(Long id) {
		employeeRepository.deleteById(id);
	}
	
	private EntityModel<Employee> toEntityModel(Employee employee, Long id) {
		EntityModel<Employee> em = EntityModel.of(employee,
			      linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
			      linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
		
		return em;
	}
	
}
