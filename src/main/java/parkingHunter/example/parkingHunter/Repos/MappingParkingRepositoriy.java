package parkingHunter.example.parkingHunter.Repos;

import org.springframework.data.repository.CrudRepository;
import parkingHunter.example.parkingHunter.Models.MappingParking;

public interface MappingParkingRepositoriy extends CrudRepository<MappingParking,Integer> {

}
