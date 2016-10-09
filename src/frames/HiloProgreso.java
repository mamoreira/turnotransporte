package frames;

public class HiloProgreso extends Thread {

	public void run(){
		boolean life=true;
		while (life) {
			PantallaCargando a=new PantallaCargando();
			for (int i = 0; i < 100; i++) {
				System.out.println("progreso "+i);
				a.setProgreso("Cargando " + i, i);
				try {
					Thread.sleep(180);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			a.dispose();
			life=false;
			
		}
	}
	
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
    	HiloProgreso p = new HiloProgreso();
    	p.start();
    	
    }
}
