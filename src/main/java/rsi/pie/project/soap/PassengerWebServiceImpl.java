package rsi.pie.project.soap;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebService;

@WebService(endpointInterface = "rsi.pie.project.soap.PassengerWebService", targetNamespace = "io.dargenn.ticketbooking")
public class PassengerWebServiceImpl extends SpringBeanAutowiringSupport implements PassengerWebService {
}
