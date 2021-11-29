package pl.kl.carfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripMapper tripMapper;
    private final TripRepository tripRepository;

    @Transactional
    public void saveTrip(TripRequest tripRequest) {
        final Trip trip = tripMapper.mapTripRequestToTrip(tripRequest);
        tripRepository.save(trip);
    }
}
