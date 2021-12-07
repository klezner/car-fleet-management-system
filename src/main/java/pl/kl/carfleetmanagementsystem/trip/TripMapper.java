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
                .departureMeterStatus(tripRequest.getDepartureMeterStatus())
                .returnMeterStatus(tripRequest.getReturnMeterStatus())
                .comments(tripRequest.getComments())
                .build();
    }

    public TripResponse mapTripToTripResponse(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .departureDate(trip.getDepartureDate())
                .returnDate(trip.getReturnDate())
                .departureMeterStatus(trip.getDepartureMeterStatus())
                .returnMeterStatus(trip.getReturnMeterStatus())
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
                .departureMeterStatus(trip.getDepartureMeterStatus())
                .returnMeterStatus(trip.getReturnMeterStatus())
                .comments(trip.getComments())
                .build();
    }
}
