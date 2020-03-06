package eg.edu.alexu.csd.datastructure.iceHockey;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;

public class PlayersFinderTest {
	PlayersFinder obj = new PlayersFinder();
	PlayersFinder obj1 = new PlayersFinder();
	
	@Test
	public void test1() {
		String photo[] = {
				"33JUBU33",
				"3U3O4433",
				"O33P44NB",
				"PO3NSDP3",
				"VNDSD333",
				"OINFD33X"
		};

		Point[] answer = new Point[3];
		answer[0] = new Point(4, 5);
		answer[1] = new Point(13, 9);
		answer[2] = new Point(14, 2);
		assertArrayEquals(answer, obj.findPlayers(photo, 3, 16));
	}
	@Test
	public void test2() {
		String photo[] = {
				"44444H44S4",
				"K444K4L444",
				"4LJ44T44XH",
				"444O4VIF44",
				"44C4D4U444",
				"4V4Y4KB4M4",
				"G4W4HP4O4W",
				"4444ZDQ4S4",
				"4BR4Y4A444",
				"4G4V4T4444"	
		};

		Point[] answer = new Point[6];
		answer[0] = new Point(3,8);
		answer[1] = new Point(4,16);
		answer[2] = new Point(5,4); 
		answer[3] = new Point(16,3);
		answer[4] = new Point(16,17);
		answer[5] = new Point(17,9);
		assertArrayEquals(answer, obj.findPlayers(photo, 4, 16));
	}
	@Test
	public void test3()
	{
		String photo[] = {
				"8D88888J8L8E888",
				"88NKMG8N8E8JI88",
				"888NS8EU88HN8EO",
				"LUQ888A8TH8OIH8",
				"888QJ88R8SG88TY",
				"88ZQV88B88OUZ8O",
				"FQ88WF8Q8GG88B8",
				"8S888HGSB8FT8S8",
				"8MX88D88888T8K8",
				"8S8A88MGVDG8XK8",
				"M88S8B8I8M88J8N",
				"8W88X88ZT8KA8I8",
				"88SQGB8I8J88W88",
				"U88H8NI8CZB88B8",
				"8PK8H8T8888TQR8"	
		};
		Point answer[] = new Point[19];
		answer[0] = new Point(1, 17); answer[1] = new Point(3, 3); answer[2] = new Point(3, 10); 
		answer[3] = new Point(3, 25); answer[4] = new Point(5, 21); answer[5] = new Point(8, 17); answer[6] = new Point(9, 2); 
		answer[7] = new Point(10,9); answer[8] = new Point(12,23); answer[9] = new Point(17,16); answer[10] = new Point(18,3);
		answer[11] = new Point(18,11); answer[12] = new Point(18,28); answer[13] = new Point(22,20); answer[14] = new Point(23,26); 
		answer[15] = new Point(24,15); answer[16] = new Point(27,2); answer[17] = new Point(28,26); answer[18] = new Point(29,16);
		
		assertArrayEquals(answer, obj.findPlayers(photo, 8, 9));
		
	}
	@Test
	public void test4()
	{
		String photo[] = {
				"11111",
				"1AAA1",
				"1A1A1",
				"1AAA1",
				"11111"
		};
		Point[] answer = new Point[2];
		answer[0] = new Point(5, 5);
		answer[1] = new Point(5, 5);
		assertArrayEquals(answer, obj.findPlayers(photo, 1, 3));
	}
	
	@Test
	public void test5() {
		String photo[] = new String[0];
		assertArrayEquals(null, obj.findPlayers(photo, 3, 3));
		photo = new String[5];
		assertArrayEquals(null, obj.findPlayers(photo, 7, 20));
		photo = null;
		assertArrayEquals(null, obj.findPlayers(photo, 8, 12));

	}
	
	
	
	
	
	
	
	

}
