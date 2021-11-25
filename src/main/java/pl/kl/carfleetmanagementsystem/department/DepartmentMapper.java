package pl.kl.carfleetmanagementsystem.department;

import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.status.Status;

@Component
public class DepartmentMapper {

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
                .status(department.getStatus())
                .build();
    }

    public DepartmentRequest mapDepartmentToDepartmentRequest(Department department) {
        return DepartmentRequest.builder()
                .id(department.getId())
                .name(department.getName())
                .abbreviation(department.getAbbreviation())
                .comment(department.getComment())
                .status(department.getStatus())
                .build();
    }
}
