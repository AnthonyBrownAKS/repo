package com.anthony.talissystem.service;

import com.anthony.talissystem.pojo.Dept;

import java.util.List;


public interface DeptService {
    /**
     *查询所有部门
     */
    List<Dept> find();

    /**
     * 添加一个部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 删除一个部门
     */
    void delete(Integer id);

    /**
     * 修改部门名称
     * @param dept
     */
    void update(Dept dept);

    /**
     * 根据id查询名称
     * @param id
     * @return
     */
    Dept selectId(Integer id);
}
