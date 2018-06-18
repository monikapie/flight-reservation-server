package rsi.pie.project.soap;

import rsi.pie.project.domain.flight.Flight;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService
public interface FlightWebService {
    @WebMethod List<Flight> getFlightsByCities(String from, String to);

    @WebMethod List<Flight> getFlightsByCitiesAndDates(String from, String to, Date departure);

    @WebMethod List<Flight> findAll();

    @WebMethod Flight findById(Long id);

    @WebMethod String bookFlight(Long flightId);
}
