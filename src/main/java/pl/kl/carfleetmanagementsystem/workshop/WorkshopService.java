package pl.kl.carfleetmanagementsystem.workshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkshopService {

    private final WorkshopMapper workshopMapper;
    private final WorkshopRepository workshopRepository;

    @Transactional
    public void saveNewWorkshop(WorkshopRequest workshopRequest) {
        final Workshop workshop = workshopMapper.mapWorkshopRequestToWorkshop(workshopRequest);
        workshopRepository.save(workshop);
    }

    public List<WorkshopResponse> fetchAllWorkshopsResponses() {
        final List<Workshop> workshopEntities = fetchAllWorkshops();
        return workshopEntities.stream()
                .map(workshopMapper::mapWorkshopToWorkshopResponse)
                .collect(Collectors.toList());
    }

    private List<Workshop> fetchAllWorkshops() {
        return workshopRepository.findAll();
    }

    public WorkshopResponse fetchWorkshopResponse(Long id) {
        final Workshop workshopEntity = fetchWorkshopById(id);
        return workshopMapper.mapWorkshopToWorkshopResponse(workshopEntity);
    }

    public Workshop fetchWorkshopById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workshop with id: " + id + " not found!"));
    }

    public WorkshopRequest fetchWorkshopRequest(Long id) {
        final Workshop workshop = fetchWorkshopById(id);
        return workshopMapper.mapWorkshopToWorkshopRequest(workshop);
    }

    @Transactional
    public void saveEditedWorkshop(WorkshopRequest workshopRequest) {
        final Workshop workshop = workshopMapper.mapWorkshopRequestToWorkshop(workshopRequest);
        workshopRepository.save(workshop);
    }

    public void deleteWorkshop(Long id) {
        workshopRepository.deleteById(id);
    }

    @Transactional
    public void setActive(Long id) {
        final Workshop workshop = fetchWorkshopById(id);
        workshop.setActive();
        workshopRepository.save(workshop);
    }

    @Transactional
    public void setInactive(Long id) {
        final Workshop workshop = fetchWorkshopById(id);
        workshop.setInactive();
        workshopRepository.save(workshop);
    }
}
