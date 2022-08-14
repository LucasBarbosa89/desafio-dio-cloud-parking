package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ParkingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Optional<Parking> findById(String id) {
        return parkingRepository.findById(id);
    }

    public Parking create(Parking parkingCreate) {
        parkingCreate.setId(getUUID());
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);

        return parkingCreate;
    }
}
