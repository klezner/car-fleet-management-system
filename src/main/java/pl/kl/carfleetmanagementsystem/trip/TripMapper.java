package pl.kl.carfleetmanagementsystem.trip;

import org.springframework.stereotype.Component;

@Component
public class TripMapper {
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
                .comments(trip.getComments())
                .build();
    }
}
