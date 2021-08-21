package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import parkingHunter.example.parkingHunter.Models.DBUser;
import parkingHunter.example.parkingHunter.Models.Dashboard;
import parkingHunter.example.parkingHunter.Models.Parking;
import parkingHunter.example.parkingHunter.Models.Reservation;
import parkingHunter.example.parkingHunter.Repos.DBUserRepository;
import parkingHunter.example.parkingHunter.Repos.DashboardRepository;
import parkingHunter.example.parkingHunter.Repos.ParkingRepository;
import parkingHunter.example.parkingHunter.Repos.ReservationRepository;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalTime;

@Controller
public class ReservationController {

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    DBUserRepository dbUserRepository;

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    DashboardRepository dashboardRepository;

    @PostMapping("/reserve")
    public RedirectView reserve(@RequestParam(value ="id") Integer idP, @RequestParam(value ="date") String date,
                                @RequestParam(value ="starTime") String starTime,
                                @RequestParam(value ="endTime") String endTime, Principal principal){

        DBUser userNames=dbUserRepository.findByUsername(principal.getName());
        String userName=userNames.getUsername();

        Parking parking= parkingRepository.findById(idP).get();
        Reservation newReservation = new Reservation(userName,date,starTime,endTime,parking);
        reservationRepository.save(newReservation);


        LocalTime t1 = LocalTime.parse(starTime);
        LocalTime t2 = LocalTime.parse(endTime);
        Duration diff = Duration.between(t1, t2);
        long totalTime=diff.toHours();
        String type="appUser";
        Dashboard dashboard=new Dashboard(date,totalTime,type,parking);
        dashboardRepository.save(dashboard);

     return new RedirectView("/");
    }


    @GetMapping("/reserve/{id}")
    public  RedirectView deleteReservation(@RequestParam(value="id")Integer id){
        System.out.println("*************************************************************");
        reservationRepository.deleteById(id);

        System.out.println(reservationRepository.findAll());

        return new RedirectView("/");
    }

    @GetMapping("/editReserve/{id}")
    public RedirectView updateTime(@RequestParam(value="id")Integer id){

        System.out.println("*********************************************************");
        Reservation res=reservationRepository.findById(id).get();
        LocalTime time = LocalTime.parse(res.getEndTime());

        LocalTime updatedTime = time.plusHours(1);
        res.setEndTime(updatedTime.toString());
        reservationRepository.save(res);
        System.out.println(res);

        return new RedirectView("/");
    }


}
