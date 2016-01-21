package pl.pilaf.inz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.pilaf.inz.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
    
    List<User> findByFirstName(String firstName);
    
    List<User> findByLastName(String lastName);
    
    List<User> findByLogin(String login);
    
}
