package nl.fhict.s3.mastermindlogic;

import nl.fhict.s3.mastermindlogic.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MastermindlogicApplicationTests {

	@Test
	void testCodeCreation() {
		//player setup a color pallet.
		Game game = new Game(1);
		Board board1 = new Board(1);
		Colour[] codeExpected = {new Colour(EPinColour.RED),
								 new Colour(EPinColour.YELLOW),
								 new Colour(EPinColour.GREEN),
								 new Colour(EPinColour.BlUE)};
		board1.createCode(codeExpected);

		Colour[] codeActual = board1.code;
		assertArrayEquals(codeExpected, codeActual);
	}

	@Test
	void setupColorCode() {
		//player setup a color pallet.
		Game game = new Game(1);
		Board board1 = new Board(1, new Colour[]{new Colour(EPinColour.RED),
				new Colour(EPinColour.YELLOW),
				new Colour(EPinColour.GREEN),
				new Colour(EPinColour.BlUE)});


		Colour[] correctColorCode={new Colour(EPinColour.RED),new Colour( EPinColour.YELLOW),new Colour(EPinColour.GREEN),new Colour( EPinColour.BlUE)};
		assertArrayEquals(board1.code,correctColorCode);

	}

	@Test
	void testGuessCodeOfOpponent() {
		Game game = new Game(1);
		EClueColour[] clues=new EClueColour[4];
		Board board1 = new Board(1, new Colour[]{new Colour(EPinColour.RED),
				new Colour(EPinColour.YELLOW),
				new Colour(EPinColour.GREEN),
				new Colour(EPinColour.BlUE)});

		Board board2 = new Board(2, new Colour[]{new Colour(EPinColour.ORANGE),
				new Colour(EPinColour.BlUE),
				new Colour(EPinColour.YELLOW),
				new Colour(EPinColour.PURPLE)});

		Player player1 = new Player(1, "JohnDoe", "secret", board1);
		Player player2 = new Player(2, "KarenMiles", "secret", board2);
		Colour[] inPutPlayer2={new Colour(EPinColour.RED),
				new Colour(EPinColour.BlUE),
				new Colour(EPinColour.YELLOW),
				new Colour(EPinColour.PURPLE)};

		clues= player1.board.getClues(inPutPlayer2);
		EClueColour[] cluesExpect=new EClueColour[]{EClueColour.BLACK,EClueColour.WHITE,EClueColour.WHITE,EClueColour.BLANK};
		assertArrayEquals(cluesExpect,clues);

	}

	@Test
	void testRightGuessCodeOfOpponent() {
		Game game = new Game(1);
		EClueColour[] clues=new EClueColour[4];
		Board board1 = new Board(1, new Colour[]{new Colour(EPinColour.RED),
				new Colour(EPinColour.YELLOW),
				new Colour(EPinColour.GREEN),
				new Colour(EPinColour.BlUE)});

		Board board2 = new Board(2, new Colour[]{new Colour(EPinColour.ORANGE),
				new Colour(EPinColour.BlUE),
				new Colour(EPinColour.YELLOW),
				new Colour(EPinColour.PURPLE)});

		Player player1 = new Player(1, "JohnDoe", "secret", board1);
		Player player2 = new Player(2, "KarenMiles", "secret", board2);
		Colour[] inPutPlayer2={new Colour(EPinColour.RED),
				new Colour(EPinColour.YELLOW),
				new Colour(EPinColour.GREEN),
				new Colour(EPinColour.BlUE)};

		clues= player1.board.getClues(inPutPlayer2);
		EClueColour[] cluesExpect=new EClueColour[]{EClueColour.BLACK,EClueColour.BLACK,EClueColour.BLACK,EClueColour.BLACK};
		assertArrayEquals(cluesExpect,clues,"ERROR COLOURS ARE NOT RIGHT");

	}



	@Test
	void colorTwoPinsRight() {
		//only two colors are right on the board. the result is that 2 pins are right.
		Game game =new Game(1);
		Board board1=new Board(1,new Colour[]{new Colour(EPinColour.BlUE), new Colour(EPinColour.BlUE), new Colour(EPinColour.GREEN), new Colour(EPinColour.ORANGE)});
		Player player1 = new Player(1, "JohnDoe", "secret", board1);

		EClueColour[] clues=new EClueColour[4];

		Colour[] input=new Colour[]{new Colour(EPinColour.BlUE),new Colour(EPinColour.BlUE),new Colour(EPinColour.BlUE),new Colour(EPinColour.BlUE)};
		EClueColour[] cluesExpect=new EClueColour[]{EClueColour.BLACK,EClueColour.BLACK,EClueColour.BLANK,EClueColour.BLANK};

		clues= player1.board.getClues(input);
		assertArrayEquals(cluesExpect,clues);
	}

	@Test
	void colorOnePinsRight() {
		//only two colors are right on the board. the result is that 2 pins are right.
		Game game =new Game(1);
		Board board1=new Board(1,new Colour[]{new Colour(EPinColour.BlUE), new Colour(EPinColour.GREEN), new Colour(EPinColour.GREEN), new Colour(EPinColour.ORANGE)});
		Player player1 = new Player(1, "JohnDoe", "secret", board1);

		EClueColour[] clues=new EClueColour[4];

		Colour[] input=new Colour[]{new Colour(EPinColour.BlUE),new Colour(EPinColour.BlUE),new Colour(EPinColour.BlUE),new Colour(EPinColour.BlUE)};
		EClueColour[] cluesExpect=new EClueColour[]{EClueColour.BLACK,EClueColour.BLANK,EClueColour.BLANK,EClueColour.BLANK};

		clues= player1.board.getClues(input);
		assertArrayEquals(cluesExpect,clues);
	}

	@Test
	void colorThreePinsRight() {
		//only two colors are right on the board. the result is that 2 pins are right.
		Game game =new Game(1);
		Board board1=new Board(1,new Colour[]{new Colour(EPinColour.BlUE), new Colour(EPinColour.BlUE), new Colour(EPinColour.BlUE), new Colour(EPinColour.ORANGE)});
		Player player1 = new Player(1, "JohnDoe", "secret", board1);

		EClueColour[] clues=new EClueColour[4];

		Colour[] input=new Colour[]{new Colour(EPinColour.BlUE),new Colour(EPinColour.BlUE),new Colour(EPinColour.BlUE),new Colour(EPinColour.BlUE)};
		EClueColour[] cluesExpect=new EClueColour[]{EClueColour.BLACK,EClueColour.BLACK,EClueColour.BLACK,EClueColour.BLANK};

		clues= player1.board.getClues(input);
		assertArrayEquals(cluesExpect,clues);
	}


	@Test
	void TestColourChecking()
	{
		Game game =new Game(1);
		Board board1=new Board(1,new Colour[]{new Colour(EPinColour.BlUE), new Colour(EPinColour.YELLOW), new Colour(EPinColour.GREEN), new Colour(EPinColour.ORANGE)});
		Player player1 = new Player(1, "JohnDoe", "secret", board1);

		EClueColour[] clues=new EClueColour[4];
		Colour[] input=new Colour[]{new Colour(EPinColour.GREEN),new Colour(EPinColour.PURPLE),new Colour(EPinColour.GREEN),new Colour(EPinColour.BlUE)};
		EClueColour[] cluesExpect=new EClueColour[]{EClueColour.BLANK,EClueColour.BLANK,EClueColour.BLACK,EClueColour.WHITE};
		clues= player1.board.getClues(input);
		assertArrayEquals(cluesExpect,clues);
	}

	@Test
	void TestInputColour()
	{
		Game game = new Game(1);
		Board board1 = new Board(1, new Colour[]{new Colour(EPinColour.RED),
				new Colour(EPinColour.YELLOW),
				new Colour(EPinColour.GREEN),
				new Colour(EPinColour.BlUE)});
		Player player1 = new Player(1, "JohnDoe", "secret", board1);

		Colour[] ExpectColour=new Colour[]{new Colour(EPinColour.RED), new Colour(EPinColour.YELLOW), new Colour(EPinColour.GREEN), new Colour(EPinColour.BlUE)};

		assertArrayEquals(ExpectColour,player1.board.code);
	}


	@Test
	void testThreeEntitiesColorCode_ShouldReturnNotEqual() {
		Colour[] code = {new Colour(EPinColour.BlUE), new Colour(EPinColour.YELLOW), new Colour(EPinColour.GREEN)};
		Board board = new Board(1, code);

		int expected = 4;
		int actual = code.length;


		assertNotEquals(expected, actual);
	}

	@Test
	void testFourEntitiesColorCode_ShouldReturnEqual() {
		Colour[] code = {new Colour(EPinColour.BlUE), new Colour(EPinColour.YELLOW), new Colour(EPinColour.GREEN), new Colour(EPinColour.ORANGE)};
		Board board = new Board(1, code);
		int expected = 4;
		int actual = code.length;

		assertEquals(expected, actual);
	}

	@Test
	void testDoubleColorColorCode_ShouldReturnEqual() {
		Colour[] code = {new Colour(EPinColour.BlUE), new Colour(EPinColour.BlUE), new Colour(EPinColour.GREEN), new Colour(EPinColour.ORANGE)};
		Board board = new Board(1, code);

		int expected = 4;
		int actual = code.length;

		assertEquals(expected, actual);
	}

	@Test
	void testNotDoubleColorColorCode_ShouldReturnEqual() {
		Colour[] code = {new Colour(EPinColour.BlUE), new Colour(EPinColour.YELLOW), new Colour(EPinColour.GREEN), new Colour(EPinColour.ORANGE)};
		Board board = new Board(1, code);

		int expected = 4;
		int actual = code.length;

		assertEquals(expected, actual);
	}


}