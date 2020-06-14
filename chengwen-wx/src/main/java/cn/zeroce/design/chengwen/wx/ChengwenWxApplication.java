package cn.zeroce.design.chengwen.wx;

import cn.zeroce.design.chengwen.wx.core.constant.ProjectConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = ProjectConstant.MAPPER_PACKAGE)
public class ChengwenWxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChengwenWxApplication.class, args);
    }

}
