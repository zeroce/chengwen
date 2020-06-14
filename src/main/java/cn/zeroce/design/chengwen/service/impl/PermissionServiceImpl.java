package cn.zeroce.design.chengwen.service.impl;

import cn.zeroce.design.chengwen.mapper.SysPermissionMapper;
import cn.zeroce.design.chengwen.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zeroce
 * @date 20.4.2 10:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<String> findPerListByUserName(String username) {
        return this.sysPermissionMapper.selectPersByUsername(username);
    }
}
