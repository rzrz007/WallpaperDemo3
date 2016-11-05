package com.example.zren.wallpaperdemo3.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gwy54 on 2016/11/5.
 */

public class ImagePath implements Serializable {

    private List<String> imagepath;
    public ImagePath(){
    }

    public List<String> getImagepath() {
        return imagepath;
    }

    public void setImagepath(List<String> imagepath) {
        this.imagepath = imagepath;
    }
}
