package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;
import parkingHunter.example.parkingHunter.Models.Parking;
import parkingHunter.example.parkingHunter.Repos.DBUserRepository;
import parkingHunter.example.parkingHunter.Repos.ParkingRepository;
import parkingHunter.example.parkingHunter.Repos.ReviewRepository;

import java.security.Principal;

@Controller
public class SendParkingDetail {
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    DBUserRepository dbUserRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @GetMapping("/{id}")
    public String sendparkingdetail(@PathVariable Integer id , Principal principal, Model model){
        if (principal != null) {
            String userType= dbUserRepository.findByUsername(principal.getName()).getAuthority();
            model.addAttribute("userType",userType);
            model.addAttribute("user", dbUserRepository.findByUsername(principal.getName()));
            Parking parking=parkingRepository.findById(id).get();
            model.addAttribute("parkingsOwner",parking);
            Iterable addingReviewId=reviewRepository.findByaddingReviewId(id);
            model.addAttribute("reviewsByBarkingId",addingReviewId);

        } else {
        }
        return "homeparking" ;
    }
}
