# FileManager

[![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](https://github.com/evdelacruz/filemanager-test/blob/master/LICENSE)

* [Technologies](#technologies)
* [Usage](#usage)

## Technologies

* Java 8
* Spring Boot 1.5.10
* Swagger 2.8.0
* Amazon AWS 1.11.285

## Usage
* FileSystem: **java** -Dstorage-type=*filesystem* -Dpath=*path* -Dmax-file-size=*size* **-jar** filemanager.jar<br>
* Metadata:   **java** -Dstorage-type=*metadata* -Dpath=*path* -Dmax-file-size=*size* **-jar** filemanager.jar<br>
* S3:   	  **java** -Dstorage-type=*s3* -Dmax-file-size=1MB **-jar** filemanager.jar<br>