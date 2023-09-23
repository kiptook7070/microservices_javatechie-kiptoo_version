package eclectics.io.employeeservice.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long departmentId;
    private String departmentName;
    private String employeeNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer nationalId;
    private String phoneNumber;
    private String emailAddress;
    private Date dob;

    //*****************Operational Audit *********************
    @Column(length = 30, nullable = false)
    @JsonIgnore
    private String postedBy;
    @Column(nullable = false)
    @JsonIgnore
    private Character postedFlag = 'Y';
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date postedTime;
    @JsonIgnore
    private String modifiedBy;
    @JsonIgnore
    private Character modifiedFlag = 'N';
    @JsonIgnore
    private Date modifiedTime;
    @JsonIgnore
    private String verifiedBy;
    @JsonIgnore
    private Character verifiedFlag = 'N';
    @JsonIgnore
    private Date verifiedTime;
    @JsonIgnore
    private String deletedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character deletedFlag = 'N';
    @JsonIgnore
    private Date deletedTime;
}
