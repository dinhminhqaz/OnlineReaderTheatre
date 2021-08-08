package finalproject;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class group {
		player player[] = new player[3];
		static String character[] = new String[3];
		
		private boolean isFull = false;
		private static Lock lock = new ReentrantLock();
		public group() {}
		
		public group(player player1, player player2, player player3) {
			if (!isFull) {} else {
			player[1] = player1;
			player[2] = player2;
			player[3] = player3;
			isFull = true;}
		}
		
		
		public static void choose(int i, player player) {
			lock.lock();
		try {	
			switch (i) {
				case 1:
					if (character[0].isEmpty()) {
						player.getusername();} else {System.out.println("Character is not free anymore!");}
			case 2: if (character[1].isEmpty()) {player.getusername();} else {System.out.println("Character is not free anymore!");}
			case 3: if (character[2].isEmpty()) {player.getusername();} else {System.out.println("Character is not free anymore!");}
			}
		} finally {
			lock.unlock();
		}
		}
	}


