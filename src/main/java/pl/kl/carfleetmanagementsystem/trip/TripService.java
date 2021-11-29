package pl.kl.carfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<TripResponse> fetchAllTripsResponses() {
        final List<Trip> tripEntities = fetchAllTrips();
        return tripEntities.stream()
                .map(tripMapper::mapTripToTripResponse)
                .collect(Collectors.toList());
    }

    private List<Trip> fetchAllTrips() {
        return tripRepository.findAll();
    }
}
