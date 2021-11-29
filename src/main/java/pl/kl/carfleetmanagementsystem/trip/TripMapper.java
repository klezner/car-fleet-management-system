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
}
