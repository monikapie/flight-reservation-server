package rsi.pie.project.domain.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rsi.pie.project.domain.airport.Airport;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByFromAndTo(Airport from, Airport to);

    List<Flight> findByFromAndToAndDepartureDateGreaterThan(Airport airportFrom, Airport airportTo, Date departure);

    Flight findByFlightId(Long flight_id);
}
