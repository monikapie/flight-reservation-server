package rsi.pie.project.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import rsi.pie.project.domain.airport.Airport;
import rsi.pie.project.domain.airport.AirportService;
import rsi.pie.project.domain.flight.Flight;
import rsi.pie.project.domain.flight.FlightService;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService(endpointInterface = "rsi.pie.project.soap.FlightWebService", targetNamespace = "io.dargenn.ticketbooking")
public class FlightWebServiceImpl extends SpringBeanAutowiringSupport implements FlightWebService {
    @Autowired private FlightService flightService;
    @Autowired private AirportService airportService;

    @Override
    public List<Flight> getFlightsByCities(String from, String to) {
        Airport airportFrom = airportService.findByName(from);
        Airport airportTo = airportService.findByName(to);
        return flightService.findByFromAndTo(airportFrom, airportTo);
    }

    @Override
    public List<Flight> getFlightsByCitiesAndDates(String from, String to, Date departure) {
        Airport airportFrom = airportService.findByName(from);
        Airport airportTo = airportService.findByName(to);
        return flightService.findByFromAndToAndDepartureDate(airportFrom, airportTo, departure);
    }

    @Override
    public List<Flight> findAll() {
        return flightService.findAll();
    }

    @Override
    public Flight findById(Long id) {
        return flightService.findById(id);
    }

    @Override
    public String bookFlight(Long flightId) {
        return flightService.bookFlight(flightId);
    }
}
