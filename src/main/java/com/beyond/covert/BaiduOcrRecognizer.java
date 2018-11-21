package com.beyond.covert;

import com.baidu.aip.ocr.AipOcr;
import com.beyond.entity.BaiduOcrResponseEntity;
import com.beyond.entity.ResponseEntity;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class BaiduOcrRecognizer implements Recognizer<ResponseEntity, String> {
    private static final String APP_ID = "10724640";
    private static final String API_KEY = "fxX18F1fhiN7kccbOpFxAkaM";
    private static final String SECRET_KEY = "0C6yqOuVKzw7PpwhIDvSZcRPpDWeRH7C";

    private AipOcr client;

    public BaiduOcrRecognizer() {
        client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
    }

    public ResponseEntity covert(String url) {
        BaiduOcrResponseEntity baiduOcrResponseEntity = new BaiduOcrResponseEntity();
        JSONObject jsonObject = client.basicGeneralUrl(url, null);
        System.out.println(jsonObject.toString());
        JSONArray words_result = jsonObject.getJSONArray("words_result");
        for (int i = 0; i < words_result.length(); i++) {
            JSONObject wordsObject = words_result.getJSONObject(i);
            if (wordsObject!=null&&wordsObject.get("words")!=null){
                baiduOcrResponseEntity.addWords(wordsObject.get("words").toString());
            }
        }
        return baiduOcrResponseEntity;
    }
}
