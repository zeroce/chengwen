package cn.zeroce.design.chengwen.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.3.20 16:54
 */
@Data
public class SysAdminDTO {

    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 3, message = "用户名长度不能小于3")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不能小于6")
    private String password;

    private String avatar;

    private String name;

    private String introduction;

    private List<Integer> roleIds;
}
