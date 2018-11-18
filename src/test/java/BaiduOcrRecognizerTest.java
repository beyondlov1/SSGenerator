import org.json.JSONObject;
import org.junit.Test;

import java.util.List;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class BaiduOcrRecognizerTest {
    @Test
    public void test(){
        Generator<String> generator = new PictureUrlGenerator();
        Recognizer<ResponseEntity,String> recognizer = new BaiduOcrRecognizer();
        ResponseEntity result = recognizer.covert(generator.generate());
        System.out.println(result.getContent());
        SSConfigSelector ssConfigSelector = new SSConfigSelectorImpl(4,4,7);
        SSConfigEntity ssConfigEntity = ssConfigSelector.selectFrom(result);
        System.out.println(ssConfigEntity.getIp());
        System.out.println(ssConfigEntity.getPort());
        System.out.println(ssConfigEntity.getPassword());
        System.out.println(ssConfigEntity.getEncryption());

        System.out.println(SSUrlCreator.create(ssConfigEntity));
    }
}
