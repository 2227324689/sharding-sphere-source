package com.example.springbootredisexample.service.impl;

import com.example.springbootredisexample.dal.model.UserInfo;
import com.example.springbootredisexample.dal.mapper.UserInfoMapper;
import com.example.springbootredisexample.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-07-15
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
