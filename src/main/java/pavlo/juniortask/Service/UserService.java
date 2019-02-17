package pavlo.juniortask.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pavlo.juniortask.Dao.UserDao;
import pavlo.juniortask.Model.User;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public void save(User user){
        if (!user.getUsername().isEmpty()){
            userDao.save(user);
        }

    }

    public List<User> find(){
      return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }

    public void transfer(MultipartFile file) throws IOException {
        String path =  System.getProperty("user.dir") + File.separator + "img" + File.separator;
        System.out.println(path);
        file.transferTo(new File(path + file.getOriginalFilename()));
    }

    public User findById(int id){
       return userDao.getOne(id);
    }



}
