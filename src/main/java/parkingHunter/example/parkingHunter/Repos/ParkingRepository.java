package parkingHunter.example.parkingHunter.Repos;

import org.springframework.data.repository.CrudRepository;
import parkingHunter.example.parkingHunter.Models.DBUser;
import parkingHunter.example.parkingHunter.Models.Parking;

public interface ParkingRepository extends CrudRepository<Parking,Integer> {
 Iterable<Parking> findAllByAddingParking(DBUser user);
}
