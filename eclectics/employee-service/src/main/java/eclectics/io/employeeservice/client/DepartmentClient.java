package eclectics.io.employeeservice.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Optional;

@HttpExchange
public interface DepartmentClient {
//    http://localhost:8060/department/find/2
    @GetExchange("/department/find/{id}")
    Optional<Department> findByDepartment(@PathVariable("id") Long id);
}
