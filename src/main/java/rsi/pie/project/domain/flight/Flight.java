package rsi.pie.project.domain.flight;

import lombok.Data;
import rsi.pie.project.domain.airport.Airport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "FLIGHT")
@Data
public class Flight implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long flightId;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Airport from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Airport to;

    @Column
    @Temporal(TemporalType.DATE)
    private Date departureDate;

    @Column
    @Temporal(TemporalType.TIME)
    private Date departureHour;
}
