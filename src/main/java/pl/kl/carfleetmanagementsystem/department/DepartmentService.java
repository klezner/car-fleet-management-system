package pl.kl.carfleetmanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    public List<DepartmentResponse> fetchAllDepartmentsResponses() {
        final List<Department> departmentEntities = fetchAllDepartments();
        return departmentEntities.stream()
                .map(departmentMapper::mapDepartmentToDepartmentResponse)
                .collect(Collectors.toList());
    }

    private List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

    public DepartmentRequest fetchDepartmentRequest(Long id) {
        final Department department = fetchDepartmentById(id);
        return departmentMapper.mapDepartmentToDepartmentRequest(department);
    }

    private Department fetchDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department with id: " + id + " not found!"));
    }

    public DepartmentResponse fetchDepartmentResponse(Long id) {
        final Department department = fetchDepartmentById(id);
        return departmentMapper.mapDepartmentToDepartmentResponse(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Transactional
    public void setActive(Long id) {
        final Department department = fetchDepartmentById(id);
        department.setActive();
        departmentRepository.save(department);
    }

    public void setInactive(Long id) {
        final Department department = fetchDepartmentById(id);
        department.setInactive();
        departmentRepository.save(department);
    }
}
