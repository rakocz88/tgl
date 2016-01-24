package pl.pilaf.inz.controller;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pilaf.inz.model.Album;
import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.Song;
import pl.pilaf.inz.repository.AlbumRepository;
import pl.pilaf.inz.repository.BandRepository;
import pl.pilaf.inz.repository.SongRepository;
import pl.pilaf.inz.wrapper.AlbumWrapper;
import pl.pilaf.inz.wrapper.SongWrapper;

@RestController
@RequestMapping(value = "/song")
public class SongController {

	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private BandRepository bandRepository;
	
	@Autowired
	private SongRepository songRepository;

	
	@RequestMapping(value="{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SongWrapper findById(@PathVariable("id") Long id) {
		return new SongWrapper(songRepository.findOne(id));
	}
	

	
	

}
