package cn.zeroce.design.chengwen;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChengwenApplication.class)
class ChengwenApplicationTests {


    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    void contextLoads() {

    }

    @Test
    public void encrypt() {
        System.out.println("encrypt: ");
    }
}
