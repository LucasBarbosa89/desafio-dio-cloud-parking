package one.digitalinnovation.parking.repository;

import one.digitalinnovation.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends JpaRepository<Parking, String> {

}
