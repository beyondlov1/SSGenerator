package com.beyond;

import com.beyond.entity.ResponseEntity;
import com.beyond.entity.SSConfigEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class SSConfigSelectorImpl implements SSConfigSelector<List<String>> {

    private int index;
    private int size;
    private int perSize;//一个配置中会识别出多少words

    public SSConfigSelectorImpl(){
    }

    public SSConfigSelectorImpl(int size,int perSize) {
        this.index = 0;
        this.size = size;
        this.perSize = perSize;
    }
    public SSConfigSelectorImpl(int index, int size,int perSize) {
        this.index = index;
        this.size = size;
        this.perSize = perSize;
    }

    public SSConfigEntity selectFrom(ResponseEntity<List<String>> responseEntity) {
        List<String> content = responseEntity.getContent();
        SSConfigEntity ssConfigEntity = new SSConfigEntity();
        if (index<size) {
            ssConfigEntity.setIp(get(content.get(perSize * index), "(\\d+\\.\\d+\\.\\d+\\.\\d+)"));
            ssConfigEntity.setPort(get(content.get(perSize * index + 1), "(\\d+)"));
            ssConfigEntity.setPassword(get(content.get(perSize * index + 2).trim(), "密*码:? ?+(.+)"));
            ssConfigEntity.setEncryption(get(content.get(perSize * index + 3), "加密方式:? ?+(.+)"));
        }
        if (ssConfigEntity.getIp()==null||
                ssConfigEntity.getPort()==null||
                ssConfigEntity.getPassword()==null||
                ssConfigEntity.getEncryption()==null)
            throw new RuntimeException("生成出错:"+ssConfigEntity.toString());
        return ssConfigEntity;
    }

    public List<SSConfigEntity> selectAll(ResponseEntity<List<String>> responseEntity) {
        List<String> content = responseEntity.getContent();

        List<SSConfigEntity> list = new ArrayList<>();
        size = content.size()/perSize;
        for (int i = 0; i < size; i++) {
            SSConfigEntity ssConfigEntity = new SSConfigEntity();
            ssConfigEntity.setIp(get(content.get(perSize * i), "(\\d+\\.\\d+\\.\\d+\\.\\d+)"));
            ssConfigEntity.setPort(get(content.get(perSize * i + 1), "(\\d+)"));
            ssConfigEntity.setPassword(get(content.get(perSize * i + 2).trim(), "密*码:? ?+(.+)"));
            ssConfigEntity.setEncryption(get(content.get(perSize * i + 3), "加密方式:? ?+(.+)"));

            if (ssConfigEntity.getIp()==null||
                    ssConfigEntity.getPort()==null||
                    ssConfigEntity.getPassword()==null||
                    ssConfigEntity.getEncryption()==null)
                throw new RuntimeException("生成出错:"+ssConfigEntity.toString());

            list.add(ssConfigEntity);
        }

        return list;
    }



    private String get(String target, String regex) {
        String result = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        if (matcher.find()){
            result = matcher.group(1);
        }
        return result;
    }
}
