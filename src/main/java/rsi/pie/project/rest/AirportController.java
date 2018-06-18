package rsi.pie.project.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsi.pie.project.domain.airport.Airport;
import rsi.pie.project.domain.airport.AirportService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AirportController {
    private final @NonNull
    AirportService airportService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Airport>> findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(AirportController.class).toUri());
        return new ResponseEntity<>(airportService.findAll(),headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{name}")
    public Airport findById(@PathVariable String name) {
        Airport foundAirport = airportService.findByName(name);
        Link link = linkTo(AirportController.class).slash(foundAirport.getName()).withSelfRel();
        foundAirport.add(link);
        return foundAirport;
    }
}
