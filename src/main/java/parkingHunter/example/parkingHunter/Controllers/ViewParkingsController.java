package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import parkingHunter.example.parkingHunter.Repos.DBUserRepository;
import parkingHunter.example.parkingHunter.Repos.ParkingRepository;

import java.security.Principal;

@Controller
public class ViewParkingsController {
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    DBUserRepository dbUserRepository;

    @GetMapping("/viewparkings")
    public String viewParkings(Principal principal, Model model){
        model.addAttribute("parkings",
                parkingRepository.findAllByAddingParking(dbUserRepository.findByUsername(principal.getName())));

        return "viewParkings";
    }
}
