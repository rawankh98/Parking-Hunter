package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import parkingHunter.example.parkingHunter.Models.Parking;
import parkingHunter.example.parkingHunter.Repos.DBUserRepository;
import parkingHunter.example.parkingHunter.Repos.ParkingRepository;
import parkingHunter.example.parkingHunter.Repos.ReservationRepository;
import parkingHunter.example.parkingHunter.Repos.ReviewRepository;

import java.security.Principal;

@Controller
public class ParkingController {
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    DBUserRepository dbUserRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/parkingcontroller/{id}")
    public String showparkingcontroller(@PathVariable Integer id, Model model, Principal principal){
        if (principal != null) {
            String userType= dbUserRepository.findByUsername(principal.getName()).getAuthority();
            model.addAttribute("userType",userType);
            model.addAttribute("user", dbUserRepository.findByUsername(principal.getName()));
            Parking parking=parkingRepository.findById(id).get();
            model.addAttribute("parkingsOwner",parking);
            Iterable addingReviewId=reviewRepository.findByaddingReviewId(id);
            model.addAttribute("reviewsByBarkingId",addingReviewId);

            Iterable oneReservations= reservationRepository.findByReserveSpaceId(id);
            model.addAttribute("oneReservation",oneReservations);

            Iterable reservations= reservationRepository.findAll();

            model.addAttribute("allReservations",reservations);

        } else {
            System.out.println("not authenticated");
        }
    return "parkingController";
    }
}
