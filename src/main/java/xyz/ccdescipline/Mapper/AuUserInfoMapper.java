package xyz.ccdescipline.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import xyz.ccdescipline.Model.AuUserInfo;

import java.util.List;

@Mapper
public interface AuUserInfoMapper extends BaseMapper<AuUserInfo> {

    // 根据用户名查询指定用户信息
    @Select("SELECT * FROM au_userinfo WHERE username = #{username}")
    @Results({
            @Result(property = "role", column = "role_id", one = @One(select = "xyz.ccdescipline.Mapper.AuRoleMapper.selectRoleById"))
    })
    AuUserInfo selectUserByUsername(String username);
}