package rsi.pie.project.domain.airport;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "AIRPORT")
@Data
public class Airport  extends ResourceSupport implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long airportId;

    @Column
    private String name;
}
