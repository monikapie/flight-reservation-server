package rsi.pie.project.domain.flight;

import com.github.javafaker.Faker;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsi.pie.project.domain.airport.Airport;
import rsi.pie.project.domain.passenger.Passenger;
import rsi.pie.project.domain.passenger.PassengerRepository;
import rsi.pie.project.domain.ticket.Ticket;
import rsi.pie.project.domain.ticket.TicketRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FlightServiceImpl implements FlightService {
    private final @NonNull FlightRepository repository;
    private final @NonNull TicketRepository ticketRepository;
    private final @NonNull
    PassengerRepository passengerRepository;

    @Override
    public List<Flight> findByFromAndTo(Airport from, Airport to) {
        return repository.findByFromAndTo(from, to);
    }

    @Override
    public List<Flight> findByFromAndToAndDepartureDate(Airport airportFrom, Airport airportTo, Date departure) {
        return repository.findByFromAndToAndDepartureDateGreaterThan(airportFrom, airportTo, departure);
    }

    @Override
    public List<Flight> findAll() {
        return repository.findAll();
    }

    @Override
    public Flight findById(Long id) {
        return repository.findByFlightId(id);
    }

    @Override
    public String bookFlight(Long flightId) {
        Flight flight = repository.findByFlightId(flightId);
        Passenger passenger = createRandomPassenger();
        passenger = passengerRepository.save(passenger);
        Ticket ticket = createTicket(flight, passenger);
        return ticketRepository.save(ticket).getCode();
    }

    private Ticket createTicket(Flight flight, Passenger passenger) {
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket.setCode(UUID.randomUUID().toString());
        return ticket;
    }

    private Passenger createRandomPassenger() {
        Passenger passenger = new Passenger();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        return passenger;
    }

    @Override
    public Flight deleteFlight(Long id){
        Flight deletedFlight = repository.findByFlightId(id);
        repository.delete(id);
        return deletedFlight;
    }
}
