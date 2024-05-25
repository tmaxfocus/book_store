package com.tmaxnoda.bookinventory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@SpringBootTest
class BookinventoryApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers()
	{
		// give
		int numberOne = 20;
		int numberTwo = 30;

		//when
		var result = underTest.add(numberOne,numberTwo);

		//
		int expected = 51;
		assertThat(result).isEqualTo(expected);
	}

	class Calculator {
		int add(int a, int b) {return a + b;};
	}

}
