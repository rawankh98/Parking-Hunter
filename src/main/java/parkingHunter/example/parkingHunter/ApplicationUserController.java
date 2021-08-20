package parkingHunter.example.parkingHunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {
    @Autowired
    DBUserRepository DBUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String conect(Principal principal,Model model) {
        if (principal != null) {
//            System.out.println(DBUserRepository.findByUsername(principal.getName()));
           String userType= DBUserRepository.findByUsername(principal.getName()).getAuthority();
           model.addAttribute("userType",userType);


//            System.out.println(principal.getName());
        } else {
            System.out.println("not authenticated");
        }
        return "homepage";
    }

    @GetMapping("/owner")
    public String owner(Principal principal) {
        System.out.println(principal.getName());
        return "addParkingForm";
    }

    @GetMapping("/adminpanel")
    @ResponseBody
    public String admain() {
        return "iam admin";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
//        model.addAttribute("errormesssage","the user is exist");
        return "signup.html";
    }

    @GetMapping("/login")
    public String getSignInPage() {
        return "signin.html";
    }

    @GetMapping("/error")
    public String showerror() {
        return "error.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "secondpass") String secondpass,
                               @RequestParam(value = "userType") String authority, Model model
    ) {
        if (password.equals(secondpass)){
        try {
            String pictureUrl = "https://t4.ftcdn.net/jpg/03/46/93/61/360_F_346936114_RaxE6OQogebgAWTalE1myseY1Hbb5qPM.jpg";
            DBUser newUser = new DBUser(username, bCryptPasswordEncoder.encode(password), authority,pictureUrl);
            DBUserRepository.save(newUser);
            return new RedirectView("/");
        } catch (Exception e) {
                    model.addAttribute("errormesssage","the user is exist");

            return new RedirectView("/signup");
        }
        }else {
            System.out.println("The two passwords not match");
            return new RedirectView("/signup");
        }


    }

    

}
