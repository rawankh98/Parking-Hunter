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
import java.time.LocalDate;
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
        String type="ROLE_USER";
        LocalTime t1 = LocalTime.parse(starTime);
        LocalTime t2 = LocalTime.parse(endTime);
        Duration diff = Duration.between(t1, t2);
        long totalTime=diff.toHours();
        Reservation newReservation = new Reservation(userName,totalTime,date,starTime,endTime,type,parking);
        reservationRepository.save(newReservation);


     return new RedirectView("/");
    }
    @PostMapping("/custmerOfOwner")
    public RedirectView custmerOfOwner(@RequestParam(value ="id") Integer id,@RequestParam(value ="userName") String userName){

//        DBUser userNames=dbUserRepository.findByUsername(principal.getName());
//        String userName=userNames.getUsername();

        Parking parking= parkingRepository.findById(id).get();
        LocalDate dateLoacl= LocalDate.now();
        String date=dateLoacl.toString();
        LocalTime startingTime=LocalTime.now();
        String starTime=startingTime.toString();
        String endTime="";
        String type="ROLE_OUTER";
        long totalTime=0;
        Reservation newReservation = new Reservation(userName,totalTime,date,starTime,endTime,type,parking);
        reservationRepository.save(newReservation);
        return new RedirectView("/");
    }
    @GetMapping("/editcustmerOfOwner/{id}")
    public RedirectView updateTimecustmerOfOwner(@RequestParam(value="id")Integer id){
        Reservation res=reservationRepository.findById(id).get();
//        LocalTime endtingTime=LocalTime.now();
//        String endingsTime=endtingTime.toString();

        res.setEndTime(LocalTime.now().toString());
        LocalTime t1 = LocalTime.parse(res.getStarTime());
        LocalTime t2 = LocalTime.parse(res.getEndTime());
        Duration diff = Duration.between(t1, t2);
        long totalTime=diff.toHours();
        res.setTotalTime(totalTime);
        reservationRepository.save(res);
        return new RedirectView("/");
    }



    @GetMapping("/reserve/{id}")
    public  RedirectView deleteReservation(@RequestParam(value="id")Integer id){

        Reservation res = reservationRepository.findById(id).get();

//        if(res.getEndTime().equals("ahmad")){
//            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
//            LocalTime startingTime=LocalTime.now();
//            String starTime=startingTime.toString();
//            res.setEndTime(starTime);
//
//            LocalTime t1 = LocalTime.parse(res.getStarTime());
//            LocalTime t2 = LocalTime.parse(res.getEndTime());
//            Duration diff = Duration.between(t1, t2);
//            long totalTime=diff.toHours();
//            String type="notUser";
//            Dashboard dashboard=new Dashboard(res.getDate(),totalTime,type,res.getReserveSpace());
//            dashboardRepository.save(dashboard);
//        }else {
            LocalTime t1 = LocalTime.parse(res.getStarTime());
            LocalTime t2 = LocalTime.parse(res.getEndTime());
            Duration diff = Duration.between(t1, t2);
            long totalTime=diff.toHours();
            String type=res.getType();
            Dashboard dashboard=new Dashboard(res.getDate(),totalTime,type,res.getReserveSpace());
            dashboardRepository.save(dashboard);
//        }

        reservationRepository.deleteById(id);

      //  System.out.println(reservationRepository.findAll());

        return new RedirectView("/");
    }

    @GetMapping("/editReserve/{id}")
    public RedirectView updateTime(@RequestParam(value="id")Integer id){

//        System.out.println("*********************************************************");

        Reservation res=reservationRepository.findById(id).get();

        LocalTime time = LocalTime.parse(res.getEndTime());

        LocalTime updatedTime = time.plusHours(1);
        res.setEndTime(updatedTime.toString());
        LocalTime t1 = LocalTime.parse(res.getStarTime());
        LocalTime t2 = LocalTime.parse(res.getEndTime());
        Duration diff = Duration.between(t1, t2);
        long totalTime=diff.toHours();
        res.setTotalTime(totalTime);
        reservationRepository.save(res);
//        System.out.println(res);

//        LocalTime t1 = LocalTime.parse(res.getStarTime());
//        Duration diff = Duration.between(t1, updatedTime);
//        long totalTime=diff.toHours();
//       // String type="appUser";
//        System.out.println("*****************************");
//        //System.out.println(totalTime);
//        Dashboard dash=dashboardRepository.findByDashbordrepoId(id);
//       // System.out.println("1111111111111111111111111111111111111111");
//        dash.setTotalTime(totalTime);
//        System.out.println(dash.getTotalTime());
//        dashboardRepository.save(dash);
////        Dashboard dashboard=dashboardRepository.

        return new RedirectView("/");
    }


}
