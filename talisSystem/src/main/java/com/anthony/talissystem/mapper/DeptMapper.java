package com.anthony.talissystem.mapper;

import com.anthony.talissystem.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    List<Dept> find();

    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    void update(Dept dept);

    @Select("select * from dept where id=#{id}")
    Dept select(Integer id);
}
