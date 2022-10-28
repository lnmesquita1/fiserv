package br.com.fiserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiserv.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
