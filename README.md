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
* **FileSystem**: `java -Dstorage-type=filesystem -Dpath=path -Dmax-file-size=size -jar filemanager.jar`
* **Metadata**:   &nbsp;&nbsp;
                  `java -Dstorage-type=metadata -Dpath=path -Dmax-file-size=size -jar filemanager.jar`
* **S3**:   	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  `java -Dstorage-type=s3 -Dmax-file-size=size -jar filemanager.jar`


Where:
- *size* can be specified like this: **1MB**
- *path* is a real path on the server
- *filesystem*, *metadata* and *s3* are the keys for the type of storage that the application will use.