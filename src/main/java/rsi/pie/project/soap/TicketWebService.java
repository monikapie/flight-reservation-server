package rsi.pie.project.soap;

import rsi.pie.project.domain.ticket.Ticket;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

@WebService
public interface TicketWebService {

    @WebMethod Ticket findByCode(String code);

    @WebMethod @MTOM byte[] getTicketConfirmation(String code) throws Exception;
}
