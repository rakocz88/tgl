package pl.pilaf.inz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.User;
import pl.pilaf.inz.model.UserBandJson;
import pl.pilaf.inz.repository.BandRepository;
import pl.pilaf.inz.repository.UserRepository;

@RestController
@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BandRepository bandRepository;

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody User user) {
        userRepository.save(user);
    }
    
    @RequestMapping(value = "/userBand", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerBand(@RequestBody UserBandJson userBand) {
	Band band =bandRepository.save(userBand.getBand());
	userBand.getUser().setBands(Arrays.asList(band));
	User user = userRepository.save(userBand.getUser());
	band.setMembers(Arrays.asList(user));
	//TODO Jak mi się będzie chciało
//	bandRepository.flus
//	bandRepository.save(band);
	
	
       System.out.println("cos");
    }
    
    
    
    
    
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllByName(@RequestParam String name){
	return userRepository.findByFirstName(name);
    }

}
