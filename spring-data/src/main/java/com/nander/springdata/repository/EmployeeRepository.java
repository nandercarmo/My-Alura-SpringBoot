package com.nander.springdata.repository;

import java.time.LocalDate;
import java.util.List;

import com.nander.springdata.orm.Employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	public List<Employee> findByName(String name);

	public List<Employee> findByNameLike(String string);

	@Query("SELECT e FROM Employee e WHERE e.name = :name AND e.salary >= :salary AND e.hiringDate = :hiringDate")
	public List<Employee> findByNameHiringDateSalaryGreather(String name, Double salary, LocalDate hiringDate);

	@Query(value = "SELECT * FROM employees e WHERE e.hiring_date >= :hiringDate", nativeQuery = true)
	public List<Employee> findByHiringDateGrather(LocalDate hiringDate);

}