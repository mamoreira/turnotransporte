package frames;

import util.Label;

public class HiloProgreso extends Thread {
	boolean life=false;
	public void run(){
		while (life) {
			PantallaCargando a=new PantallaCargando();
			a.setVisible(true);
			for (int i = 0; i < 100; i++) {
				System.out.println("progreso "+i);
				a.setProgreso("Cargando... ",30+i);
				a.repaint();
				if(!life) {
					a.setVisible(false);
					break;
				}
				try {
					Thread.sleep(180);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	 void detener(){
		 life=false;
		 try {
             sleep(20000);
         } catch (InterruptedException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
		
	 }
	void iniciar(){
		life=true;
		start();
	}
    public static void main(String[] args) {
    	HiloProgreso p = new HiloProgreso();
    	p.iniciar();
    }
}
