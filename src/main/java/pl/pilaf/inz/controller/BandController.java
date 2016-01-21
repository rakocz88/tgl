package pl.pilaf.inz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beust.jcommander.internal.Lists;

import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.User;
import pl.pilaf.inz.repository.BandRepository;

@RestController
@RequestMapping(value = "/band")
public class BandController {

    @Autowired
    private BandRepository bandRepository;
    
    @Inject
    private SessionBiz sessionBiz;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Band> findAll() {
	final List<Band> resultList = new ArrayList<>();
	final Iterable<Band> all = bandRepository.findAll();
	Consumer<Band> consLambda = (Band band) -> (resultList.add(band));
	all.forEach(consLambda);
	return resultList;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Band> findAllByName(@RequestParam String name) {
	return bandRepository.findByName(name);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Band register(@RequestBody Band user) {
	return bandRepository.save(user);
    }
    
    @RequestMapping(value="byUser",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Band> findAllForUser() {
	return bandRepository.findByMembers(Arrays.asList(sessionBiz.getUser()));
    }

}
