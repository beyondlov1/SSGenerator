/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class Main {
    public static void main(String[] args) {
        Generator<String> generator = new PictureUrlGenerator("https://raw.githubusercontent.com/Alvin9999/PAC/master/ss/ssr3529.PNG");
        Recognizer<ResponseEntity,String> recognizer = new BaiduOcrRecognizer();
        ResponseEntity result = recognizer.covert(generator.generate());
        System.out.println(result.getContent());
        SSConfigSelector ssConfigSelector = new SSConfigSelectorImpl(1,4,7);
        SSConfigEntity ssConfigEntity = ssConfigSelector.selectFrom(result);
        System.out.println(ssConfigEntity.getIp());
        System.out.println(ssConfigEntity.getPort());
        System.out.println(ssConfigEntity.getPassword());
        System.out.println(ssConfigEntity.getEncryption());

        System.out.println(SSUrlCreator.create(ssConfigEntity));
    }
}
