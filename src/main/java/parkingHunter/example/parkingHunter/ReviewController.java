package parkingHunter.example.parkingHunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

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
       Iterable parkink=parkingRepository.findAll();
       model.addAttribute("parking",parkink);
       Iterable addingReviewId=reviewRepository.findAll();
       model.addAttribute("review",addingReviewId);

    return "parkingp";
   }
//    @GetMapping("/showReview")
//    public String parkinkps(){
//
//       Iterable addingReviewId=reviewRepository.findByaddingReviewId();
//    return "review";
//    }


    @RequestMapping("/addReview")
    @PostMapping("/addReview")
    public RedirectView addReview( String body, int stars, String parkName, int idPark, Principal principal){
        DBUser userNames=dbUserRepository.findByUsername(principal.getName());
        String userName=userNames.getUsername();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String dateTime=formatter.format(date);
        Parking addingReview=parkingRepository.findById(idPark).get();
//
        parkName=addingReview.getParkingName();
        Review review=new Review(userName,body,dateTime,stars,parkName,addingReview);
        reviewRepository.save(review);
       return new RedirectView("/showReview");
   }



}
