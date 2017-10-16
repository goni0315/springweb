package com.newlecture.aop.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
public static void main(String[] args) {
	//Calculator origin = new NewlecCalculator();
	
	ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/aop/spring/aop-context.xml");
	
	Calculator cal = (Calculator) context.getBean("cal");
	
	int data = cal.add(3, 4);
	data = cal.div(3, 1);
		
	System.out.println(data);
	
	}
}
/*aop 용어를 공부해야겟군?
proxy에서 주업무와 보조업무를 처리하는데 core concern에 주업무를 연결하는걸 위빙(weaving) 그 대상은 조인포인트
특정 대상만 적용시키겠다 포인트컷
*/