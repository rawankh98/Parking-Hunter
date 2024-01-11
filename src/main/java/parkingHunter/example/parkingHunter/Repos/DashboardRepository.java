package parkingHunter.example.parkingHunter.Repos;

import org.springframework.data.repository.CrudRepository;
import parkingHunter.example.parkingHunter.Models.Dashboard;

public interface DashboardRepository extends CrudRepository <Dashboard,Integer> {
    public Dashboard findByDashbordrepoId(Integer dashbordrepoId);
}
