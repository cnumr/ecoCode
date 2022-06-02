package fr.cnumr.java.checks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

public class AvoidRepositoryCallInLoopCheck {

    public interface EmployeeRepository extends Repository<Employee, Integer>{
    
    }
    
    public class AvoidRepositoryCallInLoopCheck {
        @Autowired
        private EmployeeRepository employeeRepository;
        
        List<Employee> smellGetAllEmployeesByIds(List<Integer> ids){
            List<Employee> employees = new ArrayList<>();
            for (Integer id: ids) {
                Optional<Employee> employee = employeeRepository.findById(id);
                if (employee.isPresent()) {
                    employees.add(employee.get());
                }
            }
            return employees;
        }

        List<Employee> smellLambdaGetAllEmployeesByIds(List<Integer> ids){
            return ids.stream().map(employeeRepository::findById).map(Optional::get).collect(Collectors.toList());
        }
        
    }

}
