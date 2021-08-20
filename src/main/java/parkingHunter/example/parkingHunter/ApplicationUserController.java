package parkingHunter.example.parkingHunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
@Controller
public class ApplicationUserController {
    @Autowired
    DBUserRepository DBUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/")
    @ResponseBody
    public String conect(){

        return "connect";
    }

    @GetMapping("/owner")
    @ResponseBody

    public String owner(){
       return "Im owner";
    }

    @GetMapping("/adminpanel")
    @ResponseBody
    public String admain(){
       return "iam admin";
    }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup.html";
    }

    @GetMapping("/login")
    public String getSignInPage(){
        return "signin.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam(value="username") String username, @RequestParam(value="password") String password){
        DBUser newUser = new DBUser(username,bCryptPasswordEncoder.encode(password),"ROLE_USER");
        DBUserRepository.save(newUser);

//        ROLE_USER
        return new RedirectView("/");
    }

    

}
