package eclectics.io.departmentservice.department;

import eclectics.io.departmentservice.responses.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public EntityResponse create(Department department) {
        try {
            EntityResponse response = new EntityResponse();

            String prefixCharacters = "DEP";
            String remainingFourDigits = "";
            Optional<DepartmentRepository.getDepartmentData> departmentData = departmentRepository.findDepartment();
            if (departmentData.isPresent()) {
                String departmentCode = departmentData.get().getDepartmentCode();
                String lastFourCharacters = departmentCode.substring(departmentCode.length() - 4);
                Long lastFourDigits = Long.valueOf(lastFourCharacters);
                String newCode = String.valueOf((lastFourDigits + 1));
                do {
                    newCode = "0" + newCode;
                } while (newCode.length() < 4);

                remainingFourDigits = newCode;
            } else {
                remainingFourDigits = "0001";
            }
            String generatedDepartmentCode = prefixCharacters + remainingFourDigits;
            department.setDepartmentCode(generatedDepartmentCode);

            department.setPostedBy("SYSTEM");
            department.setPostedFlag('Y');
            department.setPostedTime(new Date());
            Department addNew = departmentRepository.save(department);
            response.setMessage("Created Successfully");
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setEntity(addNew);

            return response;
        } catch (Exception e) {
            log.info("Caught Error " + e);
            return null;
        }
    }


    public EntityResponse all() {
        try {
            EntityResponse response = new EntityResponse();
            List<Department> departments = departmentRepository.findAll();
            if (departments.size() > 0) {
                response.setMessage(HttpStatus.FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.FOUND.value());
                response.setEntity(departments);
            } else {
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
            return response;
        } catch (Exception e) {
            log.info("Caught Error " + e);
            return null;
        }
    }
    public EntityResponse find(Long id) {
        try {
            EntityResponse response = new EntityResponse();
            Optional<Department> department = departmentRepository.findById(id);
            if (department.isPresent()) {
                response.setMessage(HttpStatus.FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.FOUND.value());
                response.setEntity(department);
            } else {
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
            return response;
        } catch (Exception e) {
            log.info("Caught Error " + e);
            return null;
        }
    }
}