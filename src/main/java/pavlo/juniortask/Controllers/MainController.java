package pavlo.juniortask.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pavlo.juniortask.Model.Email;
import pavlo.juniortask.Model.User;
import pavlo.juniortask.Service.EmailService;
import pavlo.juniortask.Service.PhoneEdiror;
import pavlo.juniortask.Service.UserService;

import javax.persistence.ManyToOne;
import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@Controller
public class MainController {
    private UserService userService;
    private PasswordEncoder encoder;
    private EmailService emailService;

    @GetMapping("/")
    public String home (Model model)
    {
        model.addAttribute("user",new User());
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
    emailService.save(new Email(user));
    return "login";
}

    @PostMapping("/successURL")
    public String succes (Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User auth = (User) authentication.getPrincipal();
        model.addAttribute("user",auth);

        return "CurrentUser";
    }

    @PostMapping("/addAvatar")
    public String img (@RequestParam("img") MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User auth = (User) authentication.getPrincipal();
        auth.setImg(file.getOriginalFilename());
        userService.transfer(file);
        userService.save(auth);
        return "CurrentUser";
    }



   private PhoneEdiror phoneEdiror;

    @InitBinder("user")
    public void InitBinder(WebDataBinder binder){
        binder.registerCustomEditor(Email.class,phoneEdiror);
    }
}
