import sun.misc.BASE64Encoder;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class SSUrlCreator {
    public static String create(SSConfigEntity ssConfigEntity){
        StringBuilder sb = new StringBuilder();

        sb.append(ssConfigEntity.getEncryption());
        sb.append(":");
        sb.append(ssConfigEntity.getPassword());
        sb.append("@");
        sb.append(ssConfigEntity.getIp());
        sb.append(":");
        sb.append(ssConfigEntity.getPort());

        BASE64Encoder base64Encoder = new BASE64Encoder();
        byte[] bytes = sb.toString().getBytes();
        String encode = base64Encoder.encode(bytes);

        return "ss://"+encode;
    }
}
