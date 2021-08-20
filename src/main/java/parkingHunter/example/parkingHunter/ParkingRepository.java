package parkingHunter.example.parkingHunter;

import org.springframework.data.repository.CrudRepository;

public interface ParkingRepository extends CrudRepository<Parking,Integer> {
 Iterable<Parking> findAllByAddingParking(DBUser user);
}
