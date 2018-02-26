package org.test.filemanager.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.test.filemanager.service.StorageService;
import java.io.IOException;

@Service
@ConditionalOnProperty(name="application.storage.type", havingValue="s3")
public class AmazonS3Storage implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(AmazonS3Storage.class);
    private @Value("${application.storage.amazon-s3.bucket-name}") String bucketName;
    private @Autowired AmazonS3 client;

    @Override
    public void saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Invalid multipart file");
        }
        this.upload(file);
    }

    //<editor-fold desc="Support methods">
    private void upload(MultipartFile file) {
        try {
            logger.debug("Proceding to upload to amazon: {}", file.getOriginalFilename());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            client.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);
            //client.putObject(bucketName, file.getOriginalFilename(), <get file from MultipartFile>)
        } catch (IOException ex) {
            throw new RuntimeException("Unexpected error !!!", ex);
        }
    }
    //</editor-fold>
}
