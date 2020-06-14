package cn.zeroce.design.chengwen;

import cn.zeroce.design.chengwen.core.constant.ProjectConstant;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = ProjectConstant.MAPPER_PACKAGE)
@EnableScheduling
@EnableEncryptableProperties
@EnableTransactionManagement
@ServletComponentScan(basePackages = ProjectConstant.FILTER_PACKAGE)
public class ChengwenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChengwenApplication.class, args);
    }

}
