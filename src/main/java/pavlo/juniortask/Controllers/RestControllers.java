package pavlo.juniortask.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pavlo.juniortask.Configs.SecurityConfig;
import pavlo.juniortask.Service.UserService;

import java.io.IOException;

@RestController
public class RestControllers {
    @Autowired
    UserService userService;

    @PostMapping("/addAvatar")
    public String img (@RequestParam("img")MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        userService.transfer(file);
        return "ok";
    }
}
