package pl.kl.carfleetmanagementsystem.carworkshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarWorkshopService {

    private final CarWorkshopMapper carWorkshopMapper;
    private final CarWorkshopRepository carWorkshopRepository;

    @Transactional
    public void saveNewCarWorkshop(CarWorkshopRequest carWorkshopRequest) {
        final CarWorkshop carWorkshop = carWorkshopMapper.mapCarWorkshopRequestToCarWorkshop(carWorkshopRequest);
        carWorkshopRepository.save(carWorkshop);
    }

    public List<CarWorkshopResponse> fetchAllCarWorkshopsResponses() {
        final List<CarWorkshop> carWorkshopEntities = fetchAllCarWorkshops();
        return carWorkshopEntities.stream()
                .map(carWorkshopMapper::mapCarWorkshopToCarWorkshopResponse)
                .collect(Collectors.toList());
    }

    private List<CarWorkshop> fetchAllCarWorkshops() {
        return carWorkshopRepository.findAll();
    }

    public CarWorkshopResponse fetchCarWorkshopResponse(Long id) {
        final CarWorkshop carWorkshopEntity = fetchCarWorkshopById(id);
        return carWorkshopMapper.mapCarWorkshopToCarWorkshopResponse(carWorkshopEntity);
    }

    private CarWorkshop fetchCarWorkshopById(Long id) {
        return carWorkshopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car workshop with id: " + id + " not found!"));
    }

    public CarWorkshopRequest fetchCarWorkshopRequest(Long id) {
        final CarWorkshop carWorkshop = fetchCarWorkshopById(id);
        return carWorkshopMapper.mapCarWorkshopToCarWorkshopRequest(carWorkshop);
    }

    @Transactional
    public void saveEditedCarWorkshop(CarWorkshopRequest carWorkshopRequest) {
        final CarWorkshop carWorkshop = carWorkshopMapper.mapCarWorkshopRequestToCarWorkshop(carWorkshopRequest);
        carWorkshopRepository.save(carWorkshop);
    }

    public void deleteCarWorkshop(Long id) {
        carWorkshopRepository.deleteById(id);
    }

    @Transactional
    public void setActive(Long id) {
        final CarWorkshop carWorkshop = fetchCarWorkshopById(id);
        carWorkshop.setActive();
        carWorkshopRepository.save(carWorkshop);
    }

    @Transactional
    public void setInactive(Long id) {
        final CarWorkshop carWorkshop = fetchCarWorkshopById(id);
        carWorkshop.setInactive();
        carWorkshopRepository.save(carWorkshop);
    }
}
