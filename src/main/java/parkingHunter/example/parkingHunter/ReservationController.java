package parkingHunter.example.parkingHunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ReservationController {

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    DBUserRepository dbUserRepository;

    @Autowired
    ReservationRepository reservationRepository;


    @PostMapping("/reserve")
    public RedirectView reserve(@RequestParam(value ="id") Integer idP, @RequestParam(value ="date") String date,
                                @RequestParam(value ="starTime") String starTime,
                                @RequestParam(value ="endTime") String endTime, Principal principal){

        DBUser userNames=dbUserRepository.findByUsername(principal.getName());
        String userName=userNames.getUsername();

        Parking parking= parkingRepository.findById(idP).get();
        Reservation newReservation = new Reservation(date,starTime,endTime,parking);
        reservationRepository.save(newReservation);

        // TODO: THIS WILL BE REDIRECTED TO TEMPLATE PARKING-PROFILE AND ADDED TO THE OWNER DASHBOARD
     return new RedirectView("/----");
    }

    @GetMapping("/reservingForm")
    public String  kjh(){
        return "reserving.html";
    }
}
