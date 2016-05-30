package com.bluemedia.app.impl.util;
import static org.fest.assertions.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
public class UniqueNumberGeneratorTest {

	@Test
	public void testGeneratedNumbers(){
		Set<Long> generatedNumbers = new HashSet<>();
		for(int i = 0; i<10000; i++){
			generatedNumbers.add(UniqueNumberGenerator.generateUniqueNumber());
		}
		assertThat(generatedNumbers).hasSize(10000);
	}
	
}
