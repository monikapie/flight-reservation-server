package rsi.pie.project.domain.ticket;

public interface TicketService {
    Ticket findByCode(String code);

    byte[] getTicketConfirmation(String code) throws Exception;
}
