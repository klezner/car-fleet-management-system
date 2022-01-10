package pl.kl.carfleetmanagementsystem.fleetcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FleetCardRepository extends JpaRepository<FleetCard, Long> {
}
