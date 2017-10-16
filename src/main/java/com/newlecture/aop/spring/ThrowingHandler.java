package com.newlecture.aop.spring;

import org.springframework.aop.ThrowsAdvice;

public class ThrowingHandler implements ThrowsAdvice{
 
public void afterThrowing(ArithmeticException e ) throws Throwable{
	
	System.out.println("그극극거 뒤집어봐 예외야? :" + e.getMessage());
	//예외가 발생해야 실행되는데 주업무에서 처리를 해서 이것은 안뜸
}
}