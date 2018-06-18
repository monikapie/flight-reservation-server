package rsi.pie.project.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import rsi.pie.project.domain.airport.Airport;
import rsi.pie.project.domain.airport.AirportService;

import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "rsi.pie.project.soap.CityWebService", targetNamespace = "io.dargenn.ticketbooking")
public class CityWebServiceImpl extends SpringBeanAutowiringSupport implements CityWebService {
    @Autowired private AirportService airportService;

    @Override
    public List<String> findAll() {
        return airportService.findAll().stream().map(Airport::getName).collect(Collectors.toList());
    }
}
