package xyz.ccdescipline.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import xyz.ccdescipline.Model.AuRole;
import xyz.ccdescipline.Model.AuUserInfo;

public interface AuRoleMapper extends BaseMapper<AuRole> {
    @Select("select * from au_role where id = #{id}")
    AuRole selectRoleById(String id);
}
