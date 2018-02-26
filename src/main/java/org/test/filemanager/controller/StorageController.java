package org.test.filemanager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(description="File storage")
public class StorageController {
    private @Autowired StorageService storageService;

    @PostMapping
    @ApiOperation(value="Stores the specified file", code=204)
    @ApiResponses({
            @ApiResponse(code=204, message = "No Content"),
            @ApiResponse(code=400, message = "Bad Request"),
            @ApiResponse(code=500, message = "Internal Error")
    })
    public ResponseEntity uploadFile(@RequestParam(name="file") MultipartFile file) {
        storageService.saveFile(file);
        return noContent().build();
    }
}
