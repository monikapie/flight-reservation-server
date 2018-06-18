package rsi.pie.project.domain.ticket;

import lombok.Data;
import rsi.pie.project.domain.flight.Flight;
import rsi.pie.project.domain.passenger.Passenger;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "TICKET")
@Data
public class Ticket implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column
    private String code;
}
