package com.beyond.source;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class PictureUrlGenerator implements Generator<String> {

    //TODO: 自动查找有ss账号的图片url

    private String url;

    public PictureUrlGenerator(){

    }

    public PictureUrlGenerator(String url) {
        this.url = url;
    }

    public String generate() {
        return url;
    }

}
