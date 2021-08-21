package parkingHunter.example.parkingHunter.Repos;

import org.springframework.data.repository.CrudRepository;
import parkingHunter.example.parkingHunter.Models.DBUser;

public interface DBUserRepository extends CrudRepository<DBUser,Integer> {
    public DBUser findByUsername(String username);
}
