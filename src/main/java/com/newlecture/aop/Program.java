package com.newlecture.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Program {
public static void main(String[] args) {
	Calculator origin = new NewlecCalculator();
	/*���� ���� �Ŀ� ���� �ð����� �˰������ �������� ���� �޼ҵ� �յڿ� ����µ�
	�׷� �޼ҵ帶�� �� ����� �ϴϱ� �װ� ���� �׷��� proxy�� �̿��Ѵ�*/
	
	//proxy�� �����ؼ� ���� �־��� ������ ����
	//1. �ڹٿ��� �����ϴ� proxy �������� �ƴ�
	Calculator cal = (Calculator) Proxy.newProxyInstance(NewlecCalculator.class.getClassLoader(), new Class[] {Calculator.class}, new InvocationHandler() {
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// TODO Auto-generated method stub
			System.out.println("����ó�� ��������");
			Object result = method.invoke(origin, args);
			return result;
		}
	});
	//int data = cal.add(3, 4);
	int data = cal.add(3, 4);

	
	System.out.println(data);
	
}
}
