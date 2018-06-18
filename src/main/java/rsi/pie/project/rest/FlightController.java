package rsi.pie.project.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsi.pie.project.domain.airport.Airport;
import rsi.pie.project.domain.airport.AirportService;
import rsi.pie.project.domain.flight.Flight;
import rsi.pie.project.domain.flight.FlightResource;
import rsi.pie.project.domain.flight.FlightService;
import rsi.pie.project.exception.FlightNotFoundException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FlightController {
    private final @NonNull
    FlightService flightService;
    private final @NonNull
    AirportService airportService;
    private final @NonNull
    FlightResourceAssembler flightResourceAssembler;

    @GetMapping
    public ResponseEntity<List<FlightResource>> findAll() {
        List<Flight> flightList = flightService.findAll();
        List<FlightResource> resourceList = flightResourceAssembler.toResources(flightList);
        return new ResponseEntity<>(resourceList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResource> findById(@PathVariable Long id) throws FlightNotFoundException{
        return new ResponseEntity<>(flightResourceAssembler.toResource(flightService.findById(id)),HttpStatus.OK);
    }

    @GetMapping("/bookFlight/{id}")
    public String bookFlight(@PathVariable Long id) {
        return flightService.bookFlight(id);
    }

    @GetMapping("/byCities")
    public ResponseEntity<List<FlightResource>> getFlightsByCities(@RequestParam(value = "from") String from,
                                           @RequestParam(value = "to") String to) throws FlightNotFoundException {
        Airport airportFrom = airportService.findByName(from);
        Airport airportTo = airportService.findByName(to);
        return new ResponseEntity<>(flightResourceAssembler.toResources(flightService.findByFromAndTo(airportFrom, airportTo))
                , HttpStatus.OK);
    }

    @GetMapping("/byCitiesAndDate")
    public ResponseEntity<List<FlightResource>> getFlightsByCitiesAndDates(@RequestParam(value = "from") String from,
                                                   @RequestParam(value = "to") String to,
                                                   @RequestParam(value = "departure") Date departure) throws FlightNotFoundException{
        Airport airportFrom = airportService.findByName(from);
        Airport airportTo = airportService.findByName(to);
        return new ResponseEntity<>(flightResourceAssembler.toResources(flightService.findByFromAndToAndDepartureDate(airportFrom, airportTo, departure))
                , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightResource> cancelFlight(@PathVariable Long id){
        Flight flight = flightService.deleteFlight(id);
        FlightResource resource = flightResourceAssembler.toResource(flight);
        return new ResponseEntity<FlightResource>(resource, HttpStatus.OK);
    }

    @ExceptionHandler
    ResponseEntity handleExceptions(Exception ex) {
        ResponseEntity responseEntity = null;
        if (ex instanceof FlightNotFoundException) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
