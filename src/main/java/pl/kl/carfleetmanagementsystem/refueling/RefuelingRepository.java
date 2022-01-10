package pl.kl.carfleetmanagementsystem.refueling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;

@Repository
public interface RefuelingRepository extends JpaRepository<Refueling, Long> {
    Refueling findTopByVehicleOrderByOdometerStatusDesc(Vehicle vehicle);
}
