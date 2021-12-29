package pl.kl.carfleetmanagementsystem.repair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
    Repair findTopByVehicleOrderByLeftOdometerStatusDesc(Vehicle vehicle);
}
