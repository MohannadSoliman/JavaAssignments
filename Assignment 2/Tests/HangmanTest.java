package eg.edu.alexu.csd.datastructure.hangman.cs;

import static org.junit.Assert.*;

import org.junit.Test;

public class HangmanTest {
	Hangman obj = new Hangman();
	String words[] = {"hello", "welcome", "trial"};
	@Test
	public void testSetDictionary() {
		obj.setDictionary(words);
		assertNotNull(obj.dictionary[0]);
	}

	@Test
	public void testSelectRandomSecretWord() {
		obj.setDictionary(words);
		obj.selectRandomSecretWord();
		assertNotNull(obj.chosenWord);
	}


	@Test
	public void testSetMaxWrongGuesses() {
		obj.setMaxWrongGuesses(5);
		assertNotNull(obj.maximum);
	}

	@Test
	public void testGuess() throws Exception{
		obj.chosenWord = "hello";
		obj.maximum = 3;
		assertEquals("-----", obj.guess('a'));
		assertEquals("h----", obj.guess('H'));
		assertEquals("h-ll-", obj.guess('l'));
		obj.maximum = 0;
		assertEquals(null, obj.guess('e'));
	}
	@Test
	(expected = Exception.class)
	public void buggyWord() throws Exception
	{
		obj.chosenWord = "he&llo";
		obj.guess('a');
	}

}
