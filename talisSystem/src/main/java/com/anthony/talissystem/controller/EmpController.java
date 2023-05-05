package com.anthony.talissystem.controller;

import com.anthony.talissystem.annotation.MyLog;
import com.anthony.talissystem.pojo.Emp;
import com.anthony.talissystem.pojo.PageBean;
import com.anthony.talissystem.pojo.Result;
import com.anthony.talissystem.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("Try to select by page:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用Service分页查询
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    @MyLog
    public Result delete(@PathVariable List<Integer> ids){
        log.info("Try to DELETE emp like:{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping
    @MyLog
    public Result save(@RequestBody Emp emp){
        log.info("Try to INSERT an emp like:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("Try to SELECT id:{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @PutMapping
    @MyLog
    public Result update(@RequestBody Emp emp){
        log.info("Try to UPDATE emp:{}",emp);
        empService.update(emp);
        return Result.success();
    }

}
