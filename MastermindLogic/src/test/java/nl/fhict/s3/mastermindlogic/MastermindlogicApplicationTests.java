package nl.fhict.s3.mastermindlogic;

import nl.fhict.s3.mastermindlogic.entity.Board;
import nl.fhict.s3.mastermindlogic.entity.Colour;
import nl.fhict.s3.mastermindlogic.entity.EPinColour;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

@SpringBootTest
class MastermindlogicApplicationTests {

	@Test
	void contextLoads() {

	}

	@Test
	void testThreeEntitiesColorCode_ShouldReturnNotEqual() {
		Colour[] code = {new Colour(1, EPinColour.BlUE), new Colour(1, EPinColour.YELLOW), new Colour(1, EPinColour.GREEN)};
		Board board = new Board(1, code);

		int expected = 4;
		int actual = code.length;

		assertNotEquals(expected, actual);
	}

	@Test
	void testFourEntitiesColorCode_ShouldReturnEqual() {
		Colour[] code = {new Colour(1, EPinColour.BlUE), new Colour(1, EPinColour.YELLOW), new Colour(1, EPinColour.GREEN), new Colour(1, EPinColour.ORANGE)};
		Board board = new Board(1, code);

		int expected = 4;
		int actual = code.length;

		assertEquals(expected, actual);
	}

}
