package eclectics.io.employeeservice.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        try {
            return ResponseEntity.ok().body(employeeService.create(employee));
        } catch (Exception e) {
            log.info("Caught Error" + e);
            return null;
        }
    }
}
