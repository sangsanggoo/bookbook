package com.toyproject.bookmanagement.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.toyproject.bookmanagement.aop.annotation.ValidAspect;
import com.toyproject.bookmanagement.exception.CustomException;

@Aspect
@Component
public class ValidationAop {
	@Pointcut("@annotation(com.toyproject.bookmanagement.aop.annotation.ValidAspect)")
	private void pointCut() {}
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		// 들어오는 값들은 들고있음
		// bindingresult는 SignupReqDto에 정교식에 안맞는 에러가 bindingresult 클래스로
		// joinPoint에 넘어오고 binding result클래스인것들은 넣어주고
		// errorMap에 넣어준뒤 customException에 실행 시켜준다.
		Object[] args = joinPoint.getArgs();
		BindingResult bindingResult = null;
		for(Object arg : args) {
			if(arg.getClass() == BeanPropertyBindingResult.class) {
				bindingResult = (BindingResult) arg;
			}
		}
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> {
				errorMap.put(error.getField(),error.getDefaultMessage());
			});
			
			throw new CustomException("validation Failed",errorMap);
		}
		
		return joinPoint.proceed();
	}
 }
	
