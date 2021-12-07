package pl.kl.carfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.carfleetmanagementsystem.validator.DateValidator;
import pl.kl.carfleetmanagementsystem.validator.ReturnOdometerStatusValidator;
import pl.kl.carfleetmanagementsystem.vehicle.Vehicle;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {

    private final VehicleService vehicleService;
    private final TripMapper tripMapper;
    private final TripRepository tripRepository;

    private final Integer VEHICLE_START_MILEAGE = 1;
    private final LocalDate SYSTEM_START_DATE = LocalDate.of(2020, 1, 1);

    @Transactional
    public void saveNewTrip(TripRequest tripRequest) {
        DateValidator.validateTripDateOnTripCreate(tripRequest.getDepartureDate(), tripRequest.getReturnDate(), SYSTEM_START_DATE);
        ReturnOdometerStatusValidator.validateOdometerStatusOnTripCreate(tripRequest.getDepartureOdometerStatus(), tripRequest.getReturnOdometerStatus(), VEHICLE_START_MILEAGE);
        final Trip trip = tripMapper.mapTripRequestToTrip(tripRequest);
        addVehicleToTrip(trip, tripRequest.getVehicleId());
        tripRepository.save(trip);
    }

    private void addVehicleToTrip(Trip trip, Long vehicleId) {
        final Vehicle vehicle = vehicleService.fetchVehicleById(vehicleId);
        trip.setVehicle(vehicle);
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

    private Trip fetchTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip with id: " + id + " not found!"));
    }

    public TripResponse fetchTripResponse(Long id) {
        final Trip trip = fetchTripById(id);
        return tripMapper.mapTripToTripResponse(trip);
    }

    public TripRequest fetchTripRequest(Long id) {
        final Trip trip = fetchTripById(id);
        return tripMapper.mapTripToTripRequest(trip);
    }

    @Transactional
    public void saveEditedTrip(TripRequest tripRequest) {
        DateValidator.validateTripDateOnTripEdit(tripRequest.getDepartureDate(), tripRequest.getReturnDate());
        ReturnOdometerStatusValidator.validateOdometerStatusOnTripEdit(tripRequest.getDepartureOdometerStatus(), tripRequest.getReturnOdometerStatus());
        final Trip trip = tripMapper.mapTripRequestToTrip(tripRequest);
        addVehicleToTrip(trip, tripRequest.getVehicleId());
        tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    public LastTripDataResponse fetchLastTripDataOfVehicle(Long vehicleId) {
        final Vehicle vehicle = vehicleService.fetchVehicleById(vehicleId);
        final Trip trip = tripRepository.findTopByVehicleOrderByReturnOdometerStatus(vehicle);
        if (trip != null) {
            return LastTripDataResponse.builder()
                    .returnDate(trip.getReturnDate())
                    .returnOdometerStatus(trip.getReturnOdometerStatus())
                    .build();
        } else {
            return LastTripDataResponse.builder()
                    .returnDate(SYSTEM_START_DATE)
                    .returnOdometerStatus(VEHICLE_START_MILEAGE)
                    .build();
        }
    }
}
