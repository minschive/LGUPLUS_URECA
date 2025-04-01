package com.mycom.myapp.calc.hasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Calculator 객체 필요
// CalcMain 의 Spring Context 를 통해서 HasaCalculator 객체를 생성 (DI)
@Component
public class HasaCalculator {
	// Spring DI 를 사용하지 않는 경우
//	Calculator calculator = new Calculator();
	
	// Spring DI #1 field injection
//	@Autowired
//	Calculator calculator;
	
	// Spring DI #2 setter injection
//	Calculator calculator;
//	
//	@Autowired
//	public void setCalculator(Calculator calculator) {
//		this.calculator = calculator;
//	}
	
	// Spring DI #3 constructor injection (spring 추천 방식)
	// @Autowired 필요 X
	Calculator calculator;

//	@Autowired
	public HasaCalculator(Calculator calculator) {
		super();
		this.calculator = calculator;
	}

	public int add(int n1, int n2) {
		System.out.println("HasaCalculator add()");
		return calculator.add(n1, n2);
	}
}
