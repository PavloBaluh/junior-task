package pavlo.juniortask.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pavlo.juniortask.Dao.UserDao;
import pavlo.juniortask.Model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

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


}
