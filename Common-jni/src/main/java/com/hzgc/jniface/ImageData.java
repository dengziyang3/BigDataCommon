package com.hzgc.jniface;

import java.io.Serializable;

public class ImageData implements Serializable {
    //图片数据流
    private byte[] bImageStream;

    //图片类型
    private String imageType;

    public byte[] getbImageStream() { return bImageStream; }

    public void setbImageStream(byte[] bImageStream) { this.bImageStream = bImageStream; }

    public String getImageType() { return imageType; }

    public void setImageType(String imageType) { this.imageType = imageType; }
}
