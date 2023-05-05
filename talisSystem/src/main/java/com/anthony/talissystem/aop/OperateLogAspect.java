package com.anthony.talissystem.aop;


import com.alibaba.fastjson.JSONObject;
import com.anthony.talissystem.mapper.OperateLogMapper;
import com.anthony.talissystem.pojo.OperateLog;
import com.anthony.talissystem.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class OperateLogAspect {

//    @Pointcut("execution(* com.anthony..DeptServiceImpl.*(..))" +
//            "||execution(* com.anthony..EmpServiceImpl.*(..))")

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OperateLogMapper operateLogMapper;


    @Around("@annotation(com.anthony.talissystem.annotation.MyLog)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //操作人id-当前登陆员工的id
        //获取请求头中的jwt令牌,解析令牌
        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //调用目标方法运行
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        //方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        long costTime = end-begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}",operateLog);

        return result;
    }
}
