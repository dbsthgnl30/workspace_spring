package kr.co.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AOPTestServiceImpl implements AOPTestService {

	@Override
	public void aoptest() {
//		aop가 적용되지 않은 코드
		long start = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			System.out.println(i);
		}

		long end = System.currentTimeMillis();

		System.out.println(end - start);
	}

	@Override
	public void aoptest2() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}

	}

	@Override
	public void aoptest3() {
		for (int i = 0; i < 100000; i++) {
			System.out.println(i);
		}

	}
}
