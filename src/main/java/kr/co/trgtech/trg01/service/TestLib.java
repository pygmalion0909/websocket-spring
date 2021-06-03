package kr.co.trgtech.trg01.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestLib {
	
	@Value("{$test.ab}")
	private String value;
	
	public int getTest(int a, int b) {
		return a + b;
	}
	
	public String getPro() {
		return this.value;
	}
	
}
