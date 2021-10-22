package com.example.springbootredisexample.dal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private Long bizId;

    private String loginName;

    private String passwd;

    private String sex;

    private String nickname;

    private String email;

    private String phone;


}
