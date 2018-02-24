package org.test.filemanager.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void saveFile(MultipartFile file);
}
