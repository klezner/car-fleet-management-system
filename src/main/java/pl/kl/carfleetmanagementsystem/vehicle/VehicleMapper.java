package pl.kl.carfleetmanagementsystem.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.department.Department;
import pl.kl.carfleetmanagementsystem.department.DepartmentMapper;
import pl.kl.carfleetmanagementsystem.department.DepartmentResponse;
import pl.kl.carfleetmanagementsystem.status.Status;

@Component
@RequiredArgsConstructor
public class VehicleMapper {

    private final DepartmentMapper departmentMapper;

    public Vehicle mapVehicleRequestToVehicle(VehicleRequest vehicleRequest) {
        return Vehicle.builder()
                .id(vehicleRequest.getId())
                .brand(vehicleRequest.getBrand())
                .model(vehicleRequest.getModel())
                .registrationNumber(vehicleRequest.getRegistrationNumber())
                .vinNumber(vehicleRequest.getVinNumber())
                .productionYear(vehicleRequest.getProductionYear())
                .type(vehicleRequest.getType())
                .status(setVehicleStatus(vehicleRequest.getStatus()))
                .build();
    }

    private Status setVehicleStatus(Status status) {
        if (status == Status.INACTIVE) {
            return Status.INACTIVE;
        } else {
            return Status.ACTIVE;
        }
    }

    public VehicleResponse mapVehicleToVehicleResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .registrationNumber(vehicle.getRegistrationNumber())
                .vinNumber(vehicle.getVinNumber())
                .productionYear(vehicle.getProductionYear())
                .type(vehicle.getType())
                .department(getDepartmentResponse(vehicle))
                .status(vehicle.getStatus())
                .build();
    }

    private DepartmentResponse getDepartmentResponse(Vehicle vehicle) {
        if (vehicle.getDepartment() != null) {
            return departmentMapper.mapDepartmentToDepartmentResponse(vehicle.getDepartment());
        } else {
            return null;
        }
    }

    public VehicleRequest mapVehicleToVehicleRequest(Vehicle vehicle) {
        return VehicleRequest.builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .registrationNumber(vehicle.getRegistrationNumber())
                .vinNumber(vehicle.getVinNumber())
                .productionYear(vehicle.getProductionYear())
                .type(vehicle.getType())
                .departmentId(setDepartmentId(vehicle.getDepartment()))
                .status(vehicle.getStatus())
                .build();
    }

    private Long setDepartmentId(Department department) {
        if (department != null) {
            return department.getId();
        } else {
            return null;
        }
    }
}
