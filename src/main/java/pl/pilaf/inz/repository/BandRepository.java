package pl.pilaf.inz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.User;

@Repository
public interface BandRepository extends CrudRepository<Band,Long>{
    
    List<Band> findByName(String name);
    
    List<Band> findByGengre(String name);
    
    List<Band> findByMembers(List<User> users);
    
}
