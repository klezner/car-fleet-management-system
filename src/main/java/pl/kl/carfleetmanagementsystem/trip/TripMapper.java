package pl.kl.carfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.vehicle.VehicleMapper;

@Component
@RequiredArgsConstructor
public class TripMapper {

    private final VehicleMapper vehicleMapper;

    public Trip mapTripRequestToTrip(TripRequest tripRequest) {
        return Trip.builder()
                .id(tripRequest.getId())
                .departureDate(tripRequest.getDepartureDate())
                .returnDate(tripRequest.getReturnDate())
                .departureOdometerStatus(tripRequest.getDepartureOdometerStatus())
                .returnOdometerStatus(tripRequest.getReturnOdometerStatus())
                .comments(tripRequest.getComments())
                .build();
    }

    public TripResponse mapTripToTripResponse(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .departureDate(trip.getDepartureDate())
                .returnDate(trip.getReturnDate())
                .departureOdometerStatus(trip.getDepartureOdometerStatus())
                .returnOdometerStatus(trip.getReturnOdometerStatus())
                .distance(trip.getDistance())
                .comments(trip.getComments())
                .vehicle(vehicleMapper.mapVehicleToVehicleResponse(trip.getVehicle()))
                .build();
    }

    public TripRequest mapTripToTripRequest(Trip trip) {
        return TripRequest.builder()
                .id(trip.getId())
                .departureDate(trip.getDepartureDate())
                .returnDate(trip.getReturnDate())
                .departureOdometerStatus(trip.getDepartureOdometerStatus())
                .returnOdometerStatus(trip.getReturnOdometerStatus())
                .comments(trip.getComments())
                .build();
    }
}
