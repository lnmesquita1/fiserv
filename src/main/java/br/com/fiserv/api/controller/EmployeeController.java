package br.com.fiserv.api.controller;

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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.annotation.security.RolesAllowed;

import br.com.fiserv.entity.Employee;
import br.com.fiserv.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")
	@RolesAllowed("admin")
	public CollectionModel<EntityModel<Employee>> all() {
		return employeeService.findAll();
	}
	
	@PostMapping("/create")
	@RolesAllowed("admin")
	public ResponseEntity<EntityModel<Employee>> newEmployee(@RequestBody Employee newEmployee) {
		Employee employee = employeeService.save(newEmployee);
		return ResponseEntity //
		        .created(linkTo(methodOn(EmployeeController.class).one(employee.getId())).toUri()) //
		        .body(EntityModel.of(employee));
	}
	
	@GetMapping("/find/{id}")
	@RolesAllowed("admin")
	public EntityModel<Employee> one(@PathVariable Long id) {
		return employeeService.findOne(id);
	}
	
	@PutMapping("/update/{id}")
	@RolesAllowed("admin")
	public ResponseEntity<?> update(@RequestBody Employee newEmployee, @PathVariable Long id) {
		EntityModel<Employee> updatedEmployee = employeeService.update(newEmployee, id);

		return ResponseEntity //
				.created(linkTo(methodOn(EmployeeController.class).one(id)).toUri()) //
				.body(updatedEmployee);
	}
	
	@DeleteMapping("/delete/{id}")
	@RolesAllowed("admin")
	ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
