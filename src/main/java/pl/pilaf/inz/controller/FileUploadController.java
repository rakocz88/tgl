package pl.pilaf.inz.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pl.pilaf.inz.repository.SongRepository;

@RestController
@RequestMapping(value = "/file")
public class FileUploadController {
    
    @Autowired
    private SongRepository songRepository;

//    @RequestMapping(value = "/upload", method = RequestMethod.GET)
//    public @ResponseBody String provideUploadInfo() {
//	return "You can upload a file by posting to this same URL.";
//    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void UploadFile(MultipartHttpServletRequest request,
	    HttpServletResponse response) throws IOException {

	//Attachment attachment = new Attachment();
	Iterator<String> itr = request.getFileNames();
	MultipartFile file = request.getFile(itr.next());
	String fileName = file.getOriginalFilename();
	//attachment.setName(fileName);
	//attachmentRepository.save(attachment);
	File dir = new File("I:\\GregorianLegend\\backend\\files\\songs\\filipos");
	if (!dir.isDirectory()) {
	    dir.mkdirs();
	};
	if (dir.isDirectory()) {
	    File serverFile = new File(dir, fileName);
	    BufferedOutputStream stream = new BufferedOutputStream(
		    new FileOutputStream(serverFile));
	    stream.write(file.getBytes());
	    stream.close();
	} else {
	    System.out.println("Error Found");
	}

    }

}