package com.hzgc.jniface;

public enum ImageType {
    JPG("JPG"),
    PNG("PNG");

    private final String type;
    private ImageType(String type) {
        this.type = type;
    }
}
