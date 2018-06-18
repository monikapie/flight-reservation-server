package rsi.pie.project.rest;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import rsi.pie.project.domain.airport.Airport;
import rsi.pie.project.domain.flight.Flight;
import rsi.pie.project.domain.flight.FlightResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class FlightResourceAssembler extends ResourceAssemblerSupport<Flight,FlightResource>{

    public FlightResourceAssembler() {
        super(FlightController.class, FlightResource.class);
    }

    @Override
    public FlightResource toResource(Flight flight) {
        FlightResource flightResource = createResourceWithId(flight.getFlightId(),flight);
        Airport from = flight.getFrom();
        if(from.getLinks().size() == 0)
            from.add(linkTo(AirportController.class).slash(from.getAirportId()).withSelfRel());
        Airport to = flight.getTo();
        if(to.getLinks().size() == 0)
            to.add(linkTo(AirportController.class).slash(to.getAirportId()).withSelfRel());
        flightResource.flight = flight;
        flightResource.add(linkTo(methodOn(FlightController.class).cancelFlight(flight.getFlightId()))
                .withRel("cancel"));
        return flightResource;
    }
}
