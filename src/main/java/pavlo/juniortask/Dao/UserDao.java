package pavlo.juniortask.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pavlo.juniortask.Model.User;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
