package com.sofixit.service1.measurements;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Aspect
@Component
@RestController
public class MeasurementAspect {

    
    private List<CallTimeRange> calls = new LinkedList<>();
    private final int callsSize = 10;

    @GetMapping("/calls/timestamps")
    public List<CallTimeRange> getCallTimeStamps(){
        return calls;        
    }

    @Around("execution(* com.sofixit.service1.controller.DataGroupController.generateData(..))")
    public Object startMonitoring(ProceedingJoinPoint joinPoint){
        LocalDateTime start = LocalDateTime.now();

        try{
            return joinPoint.proceed();
        }catch(Throwable exception){
            return exception;
        }finally{
            LocalDateTime end = LocalDateTime.now();
            calls.add(new CallTimeRange(start, end));
            if(calls.size() > callsSize) calls.removeFirst();
        }
        
    }

}
