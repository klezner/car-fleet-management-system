package pl.kl.carfleetmanagementsystem.refueling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefuelingService {

    private final RefuelingMapper refuelingMapper;
    private final RefuelingRepository refuelingRepository;

    @Transactional
    public void saveNewRefueling(RefuelingRequest refuelingRequest) {
        final Refueling refueling = refuelingMapper.mapRefuelingRequestToRefueling(refuelingRequest);
        refuelingRepository.save(refueling);
    }
}
