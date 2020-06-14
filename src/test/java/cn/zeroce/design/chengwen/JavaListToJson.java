package cn.zeroce.design.chengwen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.3.30 16:05
 */
public class JavaListToJson {

    @Test
    public void listToJson() throws JsonProcessingException {
        List<String> demo = new ArrayList<>();
        demo.add("zhangdanchun");
        demo.add("yezhuoxuan");
        ObjectMapper objectMapper = new ObjectMapper();
        String demoJson = objectMapper.writeValueAsString(demo);
        System.out.println(demoJson);
    }


}
