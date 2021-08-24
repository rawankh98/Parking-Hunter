package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import java.util.List;

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

    @GetMapping("/bookingForm")
    public String book(@RequestParam(value ="id") String  idP, Model m){
        System.out.println("----------------------------");
        System.out.println(idP);
        int x = Integer.parseInt(idP);
        System.out.println(x);
        System.out.println(idP);
        Parking parking= parkingRepository.findById(x).get();
        m.addAttribute("parking",parking);
        return "userBookingForm";
    }

    @PostMapping("/reserve")
    public RedirectView reserve(@RequestParam(value ="id") Integer idP, @RequestParam(value ="date") String date,
                                @RequestParam(value ="starTime") String starTime,
                                @RequestParam(value ="endTime") String endTime, Principal principal){

        DBUser userNames=dbUserRepository.findByUsername(principal.getName());
        String userName=userNames.getUsername();

        Parking parking= parkingRepository.findById(idP).get();
        if (parking.getAvailableSpaces()>0){
            parking.setAvailableSpaces(parking.getAvailableSpaces()-1);
        }

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
        System.out.println(id +" "+ userName);
//        DBUser userNames=dbUserRepository.findByUsername(principal.getName());
//        String userName=userNames.getUsername();

        Parking parking= parkingRepository.findById(id).get();
        Integer controllerId=parking.getId();
        if (parking.getAvailableSpaces()>0){
            parking.setAvailableSpaces(parking.getAvailableSpaces()-1);
        }
        LocalDate dateLoacl= LocalDate.now();
        String date=dateLoacl.toString();
        LocalTime startingTime=LocalTime.now();
        String starTime=startingTime.toString();
        String endTime="";
        String type="ROLE_OUTER";
        long totalTime=0;
        Reservation newReservation = new Reservation(userName,totalTime,date,starTime,endTime,type,parking);
        reservationRepository.save(newReservation);
        String url= "/parkingcontroller/"+controllerId;
        return new RedirectView(url);
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

        Integer controllerId=res.getReserveSpace().getId();
        String url="http://localhost:8080/parkingcontroller/"+controllerId;
        return new RedirectView(url);
    }



    @GetMapping("/reserve/{id}")
    public  RedirectView deleteReservation(@PathVariable(value="id")Integer id){
        Reservation res = reservationRepository.findById(id).get();
        Integer controllerId=res.getReserveSpace().getId();
        Parking parking= parkingRepository.findById(res.getReserveSpace().getId()).get();
        if (parking.getAvailableSpaces()>=0&&parking.getAvailableSpaces()<parking.getNumSpaces()){
            parking.setAvailableSpaces(parking.getAvailableSpaces()+1);
        }

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

            if(totalTime < 0){
                totalTime= totalTime*(-1);
            }

            double price = parking.getPricePerHour()*totalTime;
        //System.out.println(price);

        double newCommulative=0;
        double oldPrices=0;

            List<Dashboard> dashes= (List<Dashboard>) dashboardRepository.findAll();

            for (Dashboard dash: dashes){
                oldPrices=oldPrices+dash.getPrice();

            }
            newCommulative = price+oldPrices;

            Dashboard dashboard=new Dashboard(res.getDate(),totalTime,type,price,newCommulative,res.getReserveSpace());
            dashboardRepository.save(dashboard);

        reservationRepository.deleteById(id);
        String url= "/parkingcontroller/"+controllerId;

        return new RedirectView(url);
    }

    @GetMapping("/editReserve/{id}")
    public RedirectView updateTime(@RequestParam(value="id")Integer id){

//        System.out.println("*********************************************************");
        Reservation res=reservationRepository.findById(id).get();
        Integer controllerId=res.getReserveSpace().getId();
//        System.out.println(controllerId);
//        System.out.println(id);


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
//{id}(id=${parking.id})}
       String url= "/parkingcontroller/"+controllerId;
        return new RedirectView(url);
    }


}
