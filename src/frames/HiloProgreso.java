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
				a.setProgreso("Cargando " + i, i);
				if(!life) break;
				try {
					Thread.sleep(180);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			a.dispose();
			life=false;
			a.setVisible(false);
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
	@SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
    	HiloProgreso p = new HiloProgreso();
    	p.start();
    	
    	
    }
}
