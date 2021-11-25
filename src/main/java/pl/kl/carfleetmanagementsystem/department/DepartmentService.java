package pl.kl.carfleetmanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;


    public void saveDepartment(DepartmentRequest departmentRequest) {
        final Department department = departmentMapper.mapDepartmentRequestToDepartment(departmentRequest);
        departmentRepository.save(department);
    }

    public List<DepartmentResponse> fetchAllDepartmentsReponses() {
        final List<Department> departmentEntities = fetchAllDepartments();
        return departmentEntities.stream()
                .map(departmentMapper::mapDepartmentToDepartmentResponse)
                .collect(Collectors.toList());
    }

    private List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }
}
