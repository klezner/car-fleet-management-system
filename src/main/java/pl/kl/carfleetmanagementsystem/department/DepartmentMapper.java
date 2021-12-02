package pl.kl.carfleetmanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.company.CompanyMapper;
import pl.kl.carfleetmanagementsystem.status.Status;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {

    private final CompanyMapper companyMapper;

    public Department mapDepartmentRequestToDepartment(DepartmentRequest departmentRequest) {
        return Department.builder()
                .id(departmentRequest.getId())
                .name(departmentRequest.getName())
                .abbreviation(departmentRequest.getAbbreviation())
                .comment(departmentRequest.getComment())
                .status(setDepartmentStatus(departmentRequest.getStatus()))
                .build();
    }

    private Status setDepartmentStatus(Status status) {
        if (status == Status.INACTIVE) {
            return Status.INACTIVE;
        } else {
            return Status.ACTIVE;
        }
    }

    public DepartmentResponse mapDepartmentToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .abbreviation(department.getAbbreviation())
                .comment(department.getComment())
                .company(companyMapper.mapCompanyToCompanyResponse(department.getCompany()))
                .status(department.getStatus())
                .build();
    }

    public DepartmentRequest mapDepartmentToDepartmentRequest(Department department) {
        return DepartmentRequest.builder()
                .id(department.getId())
                .name(department.getName())
                .abbreviation(department.getAbbreviation())
                .comment(department.getComment())
                .companyId(department.getCompany().getId())
                .status(department.getStatus())
                .build();
    }
}
