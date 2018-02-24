package org.test.filemanager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.test.filemanager.service.StorageService;
import java.io.File;
import java.io.IOException;

@Service
@ConditionalOnProperty(name="application.storage.type", havingValue="filesystem")
public class FileSystemStorage implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(FileSystemStorage.class);
    private @Value("${application.storage.filesystem.path}") String path;

    @Override
    public void saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Invalid multipart file");
        }
        this.save(file);
    }

    //<editor-fold desc="Support methods">
    private void save(MultipartFile file) {
        try {
            logger.debug("Proceding to save file: {}", file.getOriginalFilename());
            file.transferTo(new File(path+file.getOriginalFilename()));
        } catch (IOException ex) {
            throw new RuntimeException("Unexpected error !!!", ex);
        }
    }
    //</editor-fold>
}
