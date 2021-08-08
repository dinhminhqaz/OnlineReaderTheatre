package finalproject;


public class team {
	player playerarr[] = new player[15];
	
	public team() {}
	
	public int getlength() {
		int count = 0;
		for (int i=0; i<15; i++) {
			if (playerarr[i]!= null) {count++;} 
		}
		return count;
	}

	public void join(player player) {
		for (int i=0;i<15;i++) {
			if (playerarr[i]==null) {
				playerarr[i]=player;
			}
		}
	}
	
	public void jointeam(player playerarr[]) {
		player temp = new player();
		for (int out = 0; out < playerarr.length; out++) {
			for (int in = out; in < playerarr.length; in++) {
				if (playerarr[in].getlevel() < playerarr[out].getlevel()) {
					temp = playerarr[in];
					playerarr[in] = playerarr[out];
					playerarr[out] = playerarr[in];
				}
			}
			int group = 0;
			int start = 0;

			while (start < playerarr.length) {
				for (int i = start; i < playerarr.length; i++) {
					if (playerarr[i + 2].getlevel() - playerarr[i].getlevel() <= 2) {
						playerarr[i].setgroup("group" + group);
						playerarr[i + 1].setgroup("group" + group);
						playerarr[i + 2].setgroup("group" + group);
						group++;
						start = start + 3;
						break;
					}
				}
			}
		}

	}

}
