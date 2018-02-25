package org.test.filemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.test.filemanager.service.StorageService;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping("/api/file/upload")
public class StorageController {
    private @Autowired StorageService storageService;

    @PostMapping
    public ResponseEntity uploadFile(@RequestParam(name="file") MultipartFile file) {
        storageService.saveFile(file);
        return noContent().build();
    }
}
