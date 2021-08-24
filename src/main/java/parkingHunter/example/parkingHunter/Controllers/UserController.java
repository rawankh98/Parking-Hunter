package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import parkingHunter.example.parkingHunter.Models.Parking;
import parkingHunter.example.parkingHunter.Repos.DBUserRepository;
import parkingHunter.example.parkingHunter.Repos.ParkingRepository;
import parkingHunter.example.parkingHunter.Repos.ReviewRepository;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    DBUserRepository dbUserRepository;
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/userShowParking")
    public String userShowParking(Principal principal, Model model) {

//
        String userType= dbUserRepository.findByUsername(principal.getName()).getAuthority();
        model.addAttribute("userType",userType);
        model.addAttribute("user", dbUserRepository.findByUsername(principal.getName()));

//            model.addAttribute("parkingsOwner", parkingRepository.findAllByAddingParking(dbUserRepository.findByUsername(principal.getName())));

        Iterable parking = parkingRepository.findAll();
        System.out.println(parking);
        model.addAttribute("parkings",parking);
        System.out.println(parking);
//            Iterable addingReviewId=reviewRepository.findAll();
//            model.addAttribute("review",addingReviewId);
//
//            Iterable reservations= reservationRepository.findAll();
//
//
//            Iterable oneReservations= reservationRepository.findByUserName(principal.getName());
//
//            model.addAttribute("allReservations",reservations);
//            model.addAttribute("oneReservation",oneReservations);



        return "userShowDetails";
    }
    @GetMapping("/userShowParking/{id}")
    public String detailsOfParking(@PathVariable Integer id , Principal principal, Model model){
        if (principal != null) {
            String userType= dbUserRepository.findByUsername(principal.getName()).getAuthority();
            model.addAttribute("userType",userType);
            model.addAttribute("user", dbUserRepository.findByUsername(principal.getName()));
//            model.addAttribute("parkingsOwner", parkingRepository.findAllByAddingParking(dbUserRepository.findByUsername(principal.getName())));
            Parking parking=parkingRepository.findById(id).get();
            model.addAttribute("parking",parking);
            Iterable addingReviewId=reviewRepository.findByaddingReviewId(id);
            System.out.println(addingReviewId);
            model.addAttribute("reviewsByBarkingId",addingReviewId);

        } else {
            System.out.println("not authenticated");
        }
        return "detailsOfParking";
    }

}
