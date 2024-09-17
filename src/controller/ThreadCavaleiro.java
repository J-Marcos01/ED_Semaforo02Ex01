package controller;

import java.util.concurrent.Semaphore;

/*
 * 4 cavaleiros caminham por um corredor, simultaneamente, de 2 a 4 m por 50 ms. O corredor é
escuro, tem 2 km e em 500 m, há uma única tocha. O cavaleiro que pegar a tocha, aumenta sua
velocidade, somando mais 2 m por 50 ms ao valor que já fazia. Em 1,5 km, existe uma pedra
brilhante. O cavaleiro que pegar a pedra, aumenta sua velocidade, somando mais 2 m por 50 ms
ao valor que já fazia (O cavaleiro que já detém a tocha não poderá pegar a pedra). Ao final dos 2
km, abrem uma porta randômica km, os cavaleiros se separam com 4 portas e, um por vez pega
uma porta aleatória (que não pode repetir) e entra nela. Apenas uma porta leva à saída. As outras
3 tem monstros que os devoram.
*/
public class ThreadCavaleiro extends Thread {

	private int idCavaleiro;
	private static boolean tocha=true ; 
	private static boolean pedra=true; 
	private static int portasFim=4;
	private int portacerta=(int)((Math.random()*1.1)+4);
	private static int percuso=2000;
	private static Semaphore semaforo;
	
	
	public ThreadCavaleiro(int idCavaleiro,Semaphore semaforo) {
		this.idCavaleiro=idCavaleiro;
		this.semaforo=semaforo;
	}
	
	
	public void run ()
	{
	caminhada();
	try {
		semaforo.acquire();
		passagemPortas();	
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}finally {
		semaforo.release();
	}
	
		
	}


	private void caminhada() {
		
		int distanciaPercorrida=0;
		int bonus=0;
		int itens=0;
		
		while(distanciaPercorrida<percuso)
		{	
			distanciaPercorrida+=(int)((Math.random()*2.1)+2)+bonus;
			System.out.println("O cavaleiro :"+idCavaleiro +" já andou :" +distanciaPercorrida  );		
			if(distanciaPercorrida==500 && tocha==true && itens==0)
				{
					tocha=false;
					itens=1;
					bonus=2;
					System.out.println("O cavaleiro :"+idCavaleiro +"pegou a tocha");
				}
			if(distanciaPercorrida==1500 && pedra==true && itens==0)
			{
				pedra=false;
				bonus=2;
				itens=1;
				System.out.println("O cavaleiro :"+idCavaleiro +"pegou a pedra");
			}
			try {
			sleep(50);
			}catch (Exception e) {
					System.out.println(e.getMessage());
			}
			
		}
		
		
	}


	private void  passagemPortas() {
		
		if(portasFim>0)
		{
			int sorteio=(int)((Math.random()*4.1)+0);
			if(sorteio==portacerta||portasFim==1)//mudar logica
			{
				System.out.println("O cavaleiro " + idCavaleiro +" se Salvou");
				
			}else {
				System.out.println("O cavaleiro "+idCavaleiro + " morreu ");
			}
			portasFim--;
		}
		
		
	}
	
}
