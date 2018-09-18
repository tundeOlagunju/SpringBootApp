package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import model.DBFile;
import model.DBText;
import payload.UploadFileResponse;
import service.DBStorageService;


@RestController
public class DBFileTextController {

    private static final Logger logger = LoggerFactory.getLogger(DBFileTextController.class);

    @Autowired
    private DBStorageService DBFileStorageService;

    
    
    @PostMapping("/uploadText")
    public void uploadText(@RequestBody DBText text) {
    	
    	DBFileStorageService.storeText(text);
    }
    
    @GetMapping("/text")
    public DBText getLatestText() {
    	
    	return DBFileStorageService.getText();
    }
    
    @GetMapping("/file")
    public String getLatestId() {
    	
    	return DBFileStorageService.getFile().getId();
    }
    
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        DBFile dbFile = DBFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }


    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = DBFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}