package com.beyond.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class BaiduOcrResponseEntity implements ResponseEntity<List<String>> {

    private List<String> wordsList;

    public List<String> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }

    public void addWords(String words){
        if (wordsList!=null){
            wordsList.add(words);
        }else {
            wordsList = new ArrayList<String>();
            addWords(words);
        }
    }

    public List<String> getContent() {
        return wordsList;
    }
}
