package org.test.filemanager.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.test.filemanager.service.StorageService;

@Service
@ConditionalOnProperty(name="application.storage.type", havingValue="filesystem")
public class FileSystemStorage implements StorageService {

    @Override
    public void saveFile(MultipartFile file) {
        throw new UnsupportedOperationException("Not implemented yet !!!");
    }
}
