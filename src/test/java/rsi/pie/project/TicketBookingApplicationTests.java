package rsi.pie.project;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rsi.pie.project.domain.airport.Airport;
import rsi.pie.project.domain.airport.AirportService;
import rsi.pie.project.domain.flight.FlightService;
import rsi.pie.project.domain.passenger.PassengerService;
import rsi.pie.project.domain.ticket.Ticket;
import rsi.pie.project.domain.ticket.TicketService;

import javax.xml.bind.JAXB;
import java.io.StringWriter;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketBookingApplicationTests {
	@Autowired private AirportService airportService;
	@Autowired private PassengerService passengerService;
	@Autowired private FlightService flightService;
	@Autowired private TicketService ticketService;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(airportService);
		Assert.assertNotNull(passengerService);
		Assert.assertNotNull(flightService);
		Assert.assertNotNull(ticketService);
	}

	@Test
	@Ignore
	public void testCitySave() {
		Airport airport = new Airport();
		airport.setName("Bialystok");
		airportService.save(airport);
		List<Airport> cities = airportService.findAll();
		Assert.assertEquals(false, cities.isEmpty());
	}

	@Test
	public void testBialystokExists() {
		Airport airport = airportService.findByName("Białystok");
		Assert.assertNotNull(airport);
	}

	@Test
	public void createXml() {
		Ticket ticket = ticketService.findByCode("48234035-4068-4744-bbca-831916a96ae2");
		StringWriter sw = new StringWriter();
		JAXB.marshal(ticket, sw);
		Assert.assertNotNull(sw.toString());
	}

	@Test
	public void createPdf() throws Exception {
		ticketService.getTicketConfirmation("48234035-4068-4744-bbca-831916a96ae2");
	}
}
