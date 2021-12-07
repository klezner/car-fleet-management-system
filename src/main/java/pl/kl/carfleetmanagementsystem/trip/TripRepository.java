package pl.kl.carfleetmanagementsystem.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Trip findTopByVehicleOrderByReturnMeterStatusDesc(Vehicle vehicle);
}
