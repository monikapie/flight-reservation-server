package rsi.pie.project.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import rsi.pie.project.domain.ticket.Ticket;
import rsi.pie.project.domain.ticket.TicketService;

import javax.jws.WebService;

@WebService(endpointInterface = "rsi.pie.project.soap.TicketWebService", targetNamespace = "io.dargenn.ticketbooking")
public class TicketWebServiceImpl extends SpringBeanAutowiringSupport implements TicketWebService {
    @Autowired private TicketService ticketService;

    @Override
    public Ticket findByCode(String code) {
        return ticketService.findByCode(code);
    }

    @Override
    public byte[] getTicketConfirmation(String code) throws Exception {
        return ticketService.getTicketConfirmation(code);
    }

}
