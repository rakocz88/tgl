package pl.pilaf.inz.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pl.pilaf.inz.model.Album;
import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.Song;
import pl.pilaf.inz.repository.AlbumRepository;
import pl.pilaf.inz.repository.BandRepository;
import pl.pilaf.inz.repository.SongRepository;

@RestController
@RequestMapping(value = "/file")
public class FileUploadController {

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private BandRepository bandRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void UploadFile(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {

		// Attachment attachment = new Attachment();
		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		String fileName = file.getOriginalFilename();
		// attachment.setName(fileName);
		// attachmentRepository.save(attachment);
		File dir = new File("E:\\GregorianLegend\\backend\\files\\songs\\filipos");
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		;
		File serverFile = new File(dir, fileName);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(file.getBytes());
		stream.close();

	}

	@RequestMapping(value = "{bandId}/upload/{albumId}/{songName}/{songDescription}", method = RequestMethod.POST)
	public void UploadSong(MultipartHttpServletRequest request, HttpServletResponse response,
			@PathVariable("bandId") long bandId, @PathVariable("albumId") long albumId,
			@PathVariable("songName") String songName, @PathVariable("songDescription") String songDescription)
					throws IOException {

		Band band = bandRepository.findOne(bandId);
		Album album = albumRepository.findOne(albumId);

		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		String fileName = file.getOriginalFilename();
		String folderPath = String.format("E:\\inzynierski\\filesStorage\\%s\\%s", band.getName(), album.getName());
		File dir = new File(folderPath);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File serverFile = new File(dir, fileName);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(file.getBytes());
		String songPath = folderPath.concat(fileName);
		Song song = new Song(songName, songPath, band, album, songDescription);
		songRepository.save(song);
		stream.close();
	}

}