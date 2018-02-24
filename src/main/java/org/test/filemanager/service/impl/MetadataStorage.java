package org.test.filemanager.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.test.filemanager.service.StorageService;
import org.test.filemanager.service.domain.Metadata;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@ConditionalOnProperty(name="application.storage.type", havingValue="metadata")
public class MetadataStorage implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(FileSystemStorage.class);
    private @Autowired ObjectMapper mapper;
    private @Value("${application.storage.metadata.path}") String path;

    @Override
    public void saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Invalid multipart file");
        }
        Metadata metadata = new Metadata(file.getOriginalFilename(), file.getSize(), this.getExtension(file.getOriginalFilename()));
        this.save(metadata);
    }

    //<editor-fold desc="Support methods">
    private String getExtension(String fileName) {
        if (fileName.contains(".")) {
            String[] array = fileName.split("\\.");
            return array[array.length - 1];
        }
        return null;
    }

    private void save(Metadata metadata) {
        try {
            logger.debug("Proceding to save metadata");
            Files.write(Paths.get(path+"metadata.json"), this.mapper.writeValueAsString(metadata).getBytes());
        } catch (IOException ex) {
            throw new RuntimeException("Unexpected error !!!", ex);
        }
    }
    //</editor-fold>
}
