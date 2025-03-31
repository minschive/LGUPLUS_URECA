package com.mycom.myapp.aspect;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycom.myapp.aspect.sub.SubBusinessProcess;

public class AspectMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/aspect.xml");
		BusinessProcess bp = (BusinessProcess) context.getBean("businessProcess");
		bp.bp();
		System.out.println("----------------------------------------------------");
		
		bp.no_bp();
		System.out.println("----------------------------------------------------");
		
		bp.int_bp();
		System.out.println("----------------------------------------------------");
		
		bp.int_String_bp("s1", 0);
		System.out.println("----------------------------------------------------");
		
		SubBusinessProcess sbp = (SubBusinessProcess) context.getBean("subBusinessProcess");
		sbp.bp();
		System.out.println("----------------------------------------------------");
		
		context.close();

	}

}
