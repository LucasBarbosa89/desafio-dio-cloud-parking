package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkNFException;
import one.digitalinnovation.parking.model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import one.digitalinnovation.parking.repository.ParkRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {

    @Autowired
    private ParkRepository parkRepository;

    public ParkingService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll(){
        return parkRepository.findAll();
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        return parkRepository.findById(id).orElseThrow(() -> new ParkNFException(id));
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        parkingCreate.setId(getUUID());
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkRepository.save(parkingCreate);

        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        Parking parking = findById(id);
        parkRepository.deleteById(id);
    }

    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);

        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());
        parkRepository.save(parking);

        return parking;
    }

    @Transactional
    public Parking exit(String id){
        Parking parking = findById(id);
        parking.setEntryDate(LocalDateTime.now());

        parking.setBill(ParkingCheckout.getBill(parking));
        return parkRepository.save(parking);
    }
}
