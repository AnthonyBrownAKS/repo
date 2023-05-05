package com.anthony.talissystem.service.impl;

import com.anthony.talissystem.mapper.DeptMapper;
import com.anthony.talissystem.mapper.EmpMapper;
import com.anthony.talissystem.pojo.Dept;
import com.anthony.talissystem.pojo.DeptLog;
import com.anthony.talissystem.service.DeptLogService;
import com.anthony.talissystem.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> find() {
        return deptMapper.find();
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.delete(id);//根据id删除部门数据
            empMapper.deletebyDeptId(id);//根据部门id删除该部门下所有员工
        }finally{
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

    @Override
    public Dept selectId(Integer id) {
        return deptMapper.select(id);
    }
}
