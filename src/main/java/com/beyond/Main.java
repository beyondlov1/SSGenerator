package com.beyond;

import com.beyond.covert.BaiduOcrRecognizer;
import com.beyond.covert.Recognizer;
import com.beyond.entity.ResponseEntity;
import com.beyond.entity.SSConfigEntity;
import com.beyond.source.Generator;
import com.beyond.source.PictureUrlGenerator;
import com.beyond.utils.SSUrlCreator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String index = "0";
        if (args != null&&args.length>0){
            index = args[0];
        }
        Generator<String> generator = new PictureUrlGenerator();
        Recognizer<ResponseEntity,String> recognizer = new BaiduOcrRecognizer();
        ResponseEntity result = recognizer.covert(generator.generate());
        System.out.println("content: "+result.getContent());
        SSConfigSelector ssConfigSelector = new SSConfigSelectorImpl(Integer.valueOf(index),4,7);
        List<SSConfigEntity> list = ssConfigSelector.selectAll(result);
        StringBuilder stringBuilder = new StringBuilder();
        for (SSConfigEntity ssConfigEntity : list) {
            stringBuilder.append(SSUrlCreator.create(ssConfigEntity));
            stringBuilder.append("\r\n");
            System.out.println(ssConfigEntity.getIp());
            System.out.println(ssConfigEntity.getPort());
            System.out.println(ssConfigEntity.getPassword());
            System.out.println(ssConfigEntity.getEncryption());
        }
        writeToFile(stringBuilder.toString(),null);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void writeToFile(String str, String path) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        if (path == null){
            path = "./result-"+simpleDateFormat.format(System.currentTimeMillis())+".txt";
        }
        File file= new File(path);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)){
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
