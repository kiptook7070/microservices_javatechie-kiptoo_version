package eclectics.io.employeeservice.employee;

import eclectics.io.employeeservice.responses.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public EntityResponse create(Employee employee) {
        try {
            EntityResponse response = new EntityResponse();

            String prefixCharacters = "EMP";
            String remainingFourDigits = "";
            Optional<EmployeeRepository.getEmployeeData> employeeData = employeeRepository.findEmployee();
            System.out.println("employee " + employeeData);
            if (employeeData.isPresent()) {
                String employeeNumber = employeeData.get().getEmployeeNumber();
                String lastFourCharacters = employeeNumber.substring(employeeNumber.length() - 4);
                Long lastFourDigits = Long.valueOf(lastFourCharacters);
                String newCode = String.valueOf((lastFourDigits + 1));
                do {
                    newCode = "0" + newCode;
                } while (newCode.length() < 4);

                remainingFourDigits = newCode;
            } else {
                remainingFourDigits = "0001";
            }
            String generatedEmployeeNumber = prefixCharacters + remainingFourDigits;
            employee.setEmployeeNumber(generatedEmployeeNumber);

            employee.setPostedBy("SYSTEM");
            employee.setPostedFlag('Y');
            employee.setPostedTime(new Date());
            Employee addNew = employeeRepository.save(employee);
            response.setMessage("Created Successfully");
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setEntity(addNew);

            return response;
        } catch (Exception e) {
            log.info("Caught Error " + e);
            return null;
        }
    }
}
