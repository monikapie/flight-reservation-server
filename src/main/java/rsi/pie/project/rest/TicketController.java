package rsi.pie.project.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rsi.pie.project.domain.ticket.Ticket;
import rsi.pie.project.domain.ticket.TicketService;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TicketController {
    private final @NonNull
    TicketService ticketService;

    @GetMapping
    public Ticket findByCode(@RequestParam("code") String code) {
        return ticketService.findByCode(code);
    }

    @GetMapping("/confirmation")
    public byte[] getTicketConfirmation(@RequestParam("code") String code) throws Exception {
        return ticketService.getTicketConfirmation(code);
    }
}
