package nl.fhict.s3.mastermindlogic;

import nl.fhict.s3.mastermindlogic.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MastermindlogicApplicationTests {

	private Game game;
	private Board board1;
	private Board board2;
	private Player player1;
	private Player player2;

	@BeforeEach
	void setup(){
		game = new Game(1);
		board1 = new Board(1);
		board2 = new Board(2);
		player1 = new Player(1, "JohnDoe","secret",board1);
		player2 = new Player(2,"JaneDoe","secret",board2);
	}

	@Test
	void testCodeCreation() {
		//player setup a color pallet.
		EPinColour[] codeExpected = {EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE};
		board1.createCode(codeExpected);

		EPinColour[] codeActual = board1.code;
		assertArrayEquals(codeExpected, codeActual);
	}

	@Test
	void setupColorCode() {
		//player setup a color pallet.
		board1.setCode(new EPinColour[]{
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		});

		EPinColour[] correctColorCode = {EPinColour.RED, EPinColour.YELLOW, EPinColour.LIME, EPinColour.BLUE};
		assertArrayEquals(board1.code, correctColorCode);

	}

	@Test
	void testGuessCodeOfOpponent() {

		player1.board.setCode(new EPinColour[]{
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		});

		player2.board.setCode(new EPinColour[]{
				EPinColour.ORANGE,
				EPinColour.BLUE,
				EPinColour.YELLOW,
				EPinColour.PURPLE
		});

		EPinColour[] inPutPlayer2 = {EPinColour.RED,
				EPinColour.BLUE,
				EPinColour.YELLOW,
				EPinColour.PURPLE
		};

		EClueColour[] clues = player1.board.getClues(inPutPlayer2);
		EClueColour[] cluesExpect=new EClueColour[]{EClueColour.BLACK,EClueColour.WHITE,EClueColour.WHITE,EClueColour.BLANK};

		assertArrayEquals(cluesExpect,clues);
	}

	@Test
	void testRightGuessCodeOfOpponent() {

		EClueColour[] clues;
		player1.board.setCode(new EPinColour[]{
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		});

		player2.board.setCode(new EPinColour[]{
				EPinColour.ORANGE,
				EPinColour.BLUE,
				EPinColour.YELLOW,
				EPinColour.PURPLE
		});

		EPinColour[] inPutPlayer2 = {
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		};

		clues = player1.board.getClues(inPutPlayer2);
		EClueColour[] cluesExpect = new EClueColour[]{
				EClueColour.BLACK,
				EClueColour.BLACK,
				EClueColour.BLACK,
				EClueColour.BLACK
		};

		assertArrayEquals(cluesExpect, clues, "ERROR COLOURS ARE NOT RIGHT");
	}

	@Test
	void colorTwoPinsRight() {
		// only two colors are right on the board. the result is that 2 pins are right.
		EClueColour[] clues;
		player1.board.setCode(new EPinColour[]{
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.LIME,
				EPinColour.ORANGE
		});

		EPinColour[] input = new EPinColour[] {
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE
		};

		EClueColour[] cluesExpect = new EClueColour[] {
				EClueColour.BLACK,
				EClueColour.BLACK,
				EClueColour.BLANK,
				EClueColour.BLANK
		};

		clues = player1.board.getClues(input);
		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void colorOnePinsRight() {
		// only two colors are right on the board. the result is that 2 pins are right.

		EClueColour[] clues;
		player1.board.setCode(new EPinColour[]{
				EPinColour.BLUE,
				EPinColour.LIME,
				EPinColour.LIME,
				EPinColour.ORANGE
		});

		EPinColour[] input = new EPinColour[] {
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE
		};

		EClueColour[] cluesExpect = new EClueColour[] {
				EClueColour.BLACK,
				EClueColour.BLANK,
				EClueColour.BLANK,
				EClueColour.BLANK
		};

		clues = player1.board.getClues(input);
		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void colorThreePinsRight() {
		// only two colors are right on the board. the result is that 2 pins are right.

		EClueColour[] clues;
		player1.board.setCode(new EPinColour[]{
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.ORANGE
		});

		EPinColour[] input = new EPinColour[] {
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE
		};

		EClueColour[] cluesExpect = new EClueColour[] {
				EClueColour.BLACK,
				EClueColour.BLACK,
				EClueColour.BLACK,
				EClueColour.BLANK
		};

		clues = player1.board.getClues(input);

		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void TestColourChecking() {

		EClueColour[] clues;
		player1.board.setCode(new EPinColour[]{
				EPinColour.BLUE,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.ORANGE
		});

		EPinColour[] input = new EPinColour[] {
				EPinColour.LIME,
				EPinColour.PURPLE,
				EPinColour.LIME,
				EPinColour.BLUE
		};

		EClueColour[] cluesExpect = new EClueColour[] {
				EClueColour.BLANK,
				EClueColour.BLANK,
				EClueColour.BLACK,
				EClueColour.WHITE
		};

		clues = player1.board.getClues(input);
		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void TestInputColour() {

		player1.board.setCode(new EPinColour[]{
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		});

		EPinColour[] ExpectColour = new EPinColour[] {
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		};

		EClueColour[] cluesExpect = new EClueColour[] {
				EClueColour.BLANK,
				EClueColour.BLANK,
				EClueColour.BLACK,
				EClueColour.WHITE
		};

		assertArrayEquals(ExpectColour, player1.board.code);

	}
}