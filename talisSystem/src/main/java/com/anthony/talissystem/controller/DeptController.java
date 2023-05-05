package com.anthony.talissystem.controller;

import com.anthony.talissystem.annotation.MyLog;
import com.anthony.talissystem.pojo.Dept;
import com.anthony.talissystem.pojo.Result;
import com.anthony.talissystem.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 获取所有
     * @return
     */
    @GetMapping
    public Result find(){
        log.info("Try to get depts information");
        List<Dept> depts = deptService.find();
        return Result.success(depts);
    }

    /**
     * 添加数据
     * @param dept
     * @return
     */
    @PostMapping
    @MyLog
    public Result add(@RequestBody Dept dept){
        log.info("Try to inset an information like:{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @MyLog
    public Result delete(@PathVariable Integer id){
        log.info("Try to DELETE an information like:{}",id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 根据id传输全部数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result selectId(@PathVariable Integer id){
        log.info("Try to SELECT by id:{}",id);
        Dept dept = deptService.selectId(id);
        return Result.success(dept);
    }

    /**
     * 根据id修改部门名称
     * @param dept
     * @return
     */
    @PutMapping
    @MyLog
    public Result update(@RequestBody Dept dept){
        log.info("Try to UPDATE an information like:{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
