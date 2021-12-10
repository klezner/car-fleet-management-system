package pl.kl.carfleetmanagementsystem.carworkshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
