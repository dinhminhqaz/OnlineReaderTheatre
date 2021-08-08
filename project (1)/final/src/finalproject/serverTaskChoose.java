package finalproject;

import java.net.Socket;


public class serverTaskChoose implements Runnable {
		private Socket connection;
		private int i;
		private player player;

		public serverTaskChoose(Socket s) {
			this.connection = s;
		}

	
		@Override
		public void run() {
			group.choose(i, player);
		}
	}


