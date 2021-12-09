package pl.kl.carfleetmanagementsystem.refueling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<RefuelingResponse> getchAllRefuelingsResponses() {
        final List<Refueling> refuelingEntities = fetchAllRefuelings();
        return refuelingEntities.stream()
                .map(refuelingMapper::mapRefuelingToRefuelingResponse)
                .collect(Collectors.toList());
    }

    private List<Refueling> fetchAllRefuelings() {
        return refuelingRepository.findAll();
    }

    public RefuelingResponse fetchRefuelingResponse(Long id) {
        final Refueling refueling = fetchRefuelingById(id);
        return refuelingMapper.mapRefuelingToRefuelingResponse(refueling);
    }

    private Refueling fetchRefuelingById(Long id) {
        return refuelingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Refueling with id: " + id + " not found!"));
    }

    public RefuelingRequest fetchRefuelingRequest(Long id) {
        final Refueling refueling = fetchRefuelingById(id);
        return refuelingMapper.mapRefuelingToRefuelingRequest(refueling);
    }

    @Transactional
    public void saveEditedRefueling(RefuelingRequest refuelingRequest) {
        final Refueling refueling = refuelingMapper.mapRefuelingRequestToRefueling(refuelingRequest);
        refuelingRepository.save(refueling);
    }
}
