package pl.pilaf.inz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pilaf.inz.model.Album;
import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.repository.AlbumRepository;
import pl.pilaf.inz.repository.BandRepository;
import pl.pilaf.inz.wrapper.AlbumWrapper;

@RestController
@RequestMapping(value = "/album")
public class AlbumController {

	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private BandRepository bandRepository;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AlbumWrapper create(@RequestBody AlbumWrapper albumWrapper) {
		Band band  = bandRepository.findOne(albumWrapper.getBand());
		Album album = new Album(albumWrapper);
		album.setBand(band);
		band.getAlbums().add(album);
		Album createdAlbum =  albumRepository.save(album);
		bandRepository.save(band);
		return new AlbumWrapper(createdAlbum);
	}

//	@RequestMapping(value = "{id}/byBand", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<AlbumWrapper> findAllForBand(@PathVariable("id") Long id) {
//		Band band = bandRepository.findOne(id);
//		Function<Album, AlbumWrapper> albumFunction = (Album album)->(new AlbumWrapper(album));
//		List<AlbumWrapper> responseList =  band.getAlbums().stream().map(albumFunction).collect(Collectors.toList());
//		return responseList;
//	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AlbumWrapper findById(@PathVariable("id") Long id) {
		return new AlbumWrapper(albumRepository.findOne(id));
	}
	
	

}
