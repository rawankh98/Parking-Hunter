package parkingHunter.example.parkingHunter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import parkingHunter.example.parkingHunter.DBUser;
import parkingHunter.example.parkingHunter.DBUserRepository;
import parkingHunter.example.parkingHunter.Parking;
import parkingHunter.example.parkingHunter.ParkingRepository;

import java.security.Principal;

@Controller
public class AddParkingController {

    @Autowired
    DBUserRepository DBUserRepository;
    @Autowired
    ParkingRepository parkingRepository;

    @GetMapping("/addparking")
    public String showAddparkingForm(){
        return "addParkingForm";
    }
    @PostMapping("/addparking")
//    @ResponseBody
    public String addParkingFromForm(
            @RequestParam(value = "parkingName") String parkingName,
            @RequestParam(value = "region") String region,
            @RequestParam(value = "numSpaces") String numSpaces,
            @RequestParam(value = "openingHour") String openingHour,
            @RequestParam(value = "closingHour") String closingHour,
            @RequestParam(value = "pricePerHour") String pricePerHour,
            @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon, Principal principal){
        String newUser= DBUserRepository.findByUsername(principal.getName()).getUsername();
        DBUser user= DBUserRepository.findByUsername(principal.getName());
        Parking newParking=new Parking(parkingName,region,lat,lon,numSpaces,openingHour,closingHour,pricePerHour,
                newUser,user);
        parkingRepository.save(newParking);
        System.out.println(newParking);

        return "homepage";
    }
}
