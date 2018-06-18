package rsi.pie.project.domain.airport;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AirportServiceImpl implements AirportService {
    private final @NonNull
    AirportRepository repository;

    @Override
    public List<Airport> findAll() {
        return repository.findAll();
    }

    @Override
    public Airport save(Airport airport) {
        return repository.save(airport);
    }

    @Override
    public Airport findByName(String name) {
        return repository.findByName(name);
    }
}
