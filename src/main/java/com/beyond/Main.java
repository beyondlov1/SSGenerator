package com.beyond;

import com.beyond.recognizer.BaiduOcrRecognizer;
import com.beyond.recognizer.Recognizer;
import com.beyond.entity.ResponseEntity;
import com.beyond.entity.SSConfigEntity;
import com.beyond.source.Generator;
import com.beyond.source.PictureUrlGenerator;
import com.beyond.utils.SSUrlCreator;
import org.apache.commons.lang3.time.DateFormatUtils;

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
        Generator<String> generator = new PictureUrlGenerator();
        if (generator instanceof PictureUrlGenerator){
            ((PictureUrlGenerator) generator).generatePicture("./result_pic.png");
        }
        Recognizer<ResponseEntity, String> recognizer = new BaiduOcrRecognizer();
        ResponseEntity result = recognizer.parse(generator.generate());
        System.out.println("content: " + result.getContent());
        SSConfigSelector ssConfigSelector = new SSConfigSelectorImpl(4, 7);
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
        writeToFile(stringBuilder.toString());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void writeToFile(String str) {
        String path = "./result.txt";
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
