package pavlo.juniortask.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pavlo.juniortask.Model.User;
import pavlo.juniortask.Service.UserService;

import javax.persistence.ManyToOne;
import java.util.List;


@AllArgsConstructor
@Controller
public class MainController {
    UserService userService;
    PasswordEncoder encoder;

    @GetMapping("/")
    public String home (){
        return "home";
    }

@PostMapping("/saveUser")
public String save (User user, Model model){
    List<User> users = userService.find();
    for (User user1 : users) {
        if (user1.getUsername().equals(user.getUsername())){
            model.addAttribute("exist","this username is already taken");
            return "home";
        }
    }
        user.setPassword( encoder.encode(user.getPassword()));
    userService.save(user);
    return "login";
}

    @PostMapping("/successURL")
    public String succes (Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication);
        model.addAttribute("user",authentication.getName());
        return "CurrentUser";
    }
}
