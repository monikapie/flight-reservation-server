package rsi.pie.project.domain.passenger;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PassengerServiceImpl implements PassengerService {
    private final @NonNull PassengerRepository repository;
}
