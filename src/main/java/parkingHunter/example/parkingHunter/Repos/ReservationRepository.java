package parkingHunter.example.parkingHunter.Repos;

import org.springframework.data.repository.CrudRepository;
import parkingHunter.example.parkingHunter.Models.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation,Integer> {
}
