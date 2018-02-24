package org.test.filemanager.service.domain;

import java.io.Serializable;

public class Metadata implements Serializable {
    private static final long serialVersionUID = 206285268210682041L;
    private String name;
    private long size;
    private String extension;

    public Metadata() {}

    public Metadata(String name, long size, String extension) {
        this.name = name;
        this.size = size;
        this.extension = extension;
    }

    //<editor-fold desc="Encapsulation">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    //</editor-fold>
}
