package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCavaleiro;


public class Cavaleiro {

	public static void main(String[] args) {

		int perm=1;
		Semaphore semaforo = new Semaphore(perm);
		
		for(int i = 0; i < 4; i++) {
			Thread t = new ThreadCavaleiro(i + 1,semaforo);
			t.start();
		
		
		}
		}
	}


