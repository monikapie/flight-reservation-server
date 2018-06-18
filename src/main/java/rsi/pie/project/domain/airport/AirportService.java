package rsi.pie.project.domain.airport;

import java.util.List;

public interface AirportService {
    List<Airport> findAll();

    Airport save(Airport airport);

    Airport findByName(String name);
}
