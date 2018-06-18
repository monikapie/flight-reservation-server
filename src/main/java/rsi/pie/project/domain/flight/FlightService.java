package rsi.pie.project.domain.flight;

import rsi.pie.project.domain.airport.Airport;

import java.util.Date;
import java.util.List;

public interface FlightService {
    List<Flight> findByFromAndTo(Airport from, Airport to);

    List<Flight> findByFromAndToAndDepartureDate(Airport airportFrom, Airport airportTo, Date departure);

    List<Flight> findAll();

    Flight findById(Long id);

    String bookFlight(Long flightId);

    Flight deleteFlight(Long id);
}
