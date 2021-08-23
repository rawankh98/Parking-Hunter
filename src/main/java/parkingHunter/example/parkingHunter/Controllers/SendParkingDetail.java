package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
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
    @GetMapping("/sendparkingdetail/{id}")
    public RedirectView sendparkingdetail(@PathVariable Integer id , Principal principal, Model model){
        System.out.println(id);
        if (principal != null) {

            model.addAttribute("parkingsOwner",
                    parkingRepository.findAllByAddingParking(dbUserRepository.findByUsername(principal.getName())));

            Iterable addingReviewId=reviewRepository.findByaddingReviewId(id);
            System.out.println(addingReviewId);
            model.addAttribute("reviewsByBarkingId",addingReviewId);

        } else {
            System.out.println("not authenticated");
        }
        return new RedirectView("/") ;
    }
}
