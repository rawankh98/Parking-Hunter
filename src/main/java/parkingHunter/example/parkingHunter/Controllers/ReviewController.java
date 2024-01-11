package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import parkingHunter.example.parkingHunter.Models.DBUser;
import parkingHunter.example.parkingHunter.Models.Parking;
import parkingHunter.example.parkingHunter.Models.Review;
import parkingHunter.example.parkingHunter.Repos.DBUserRepository;
import parkingHunter.example.parkingHunter.Repos.ParkingRepository;
import parkingHunter.example.parkingHunter.Repos.ReviewRepository;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    DBUserRepository dbUserRepository;
   @GetMapping("/parkinkp")
   public String parkinkp(Model model){

       Iterable addingReviewId=reviewRepository.findAll();
       model.addAttribute("review",addingReviewId);

    return "parkingp";
   }



    @RequestMapping("/addReview")
    @PostMapping("/addReview")
    public RedirectView addReview( String body,  int idPark, Principal principal) throws MalformedURLException {
       //int stars, String parkName,
        DBUser userNames=dbUserRepository.findByUsername(principal.getName());
        String userName=userNames.getUsername();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String dateTime=formatter.format(date);
        Parking addingReview=parkingRepository.findById(idPark).get();

        String parkName=addingReview.getParkingName();
        Review review=new Review(userName,body,dateTime,parkName,addingReview);
        reviewRepository.save(review);

        String ahmad="/userShowParking/"+idPark;
       return new RedirectView(ahmad);
   }



}
