package frames;

import com.sun.javafx.font.Disposer;
import javax.swing.JFrame;
import sun.java2d.d3d.D3DRenderQueue;

/**
 *
 * @author xavier
 */
public class ProgresoRun implements Runnable{
    private BarraDeProgreso p;

    public ProgresoRun(BarraDeProgreso p) {
        this.p = p;
    }
    
    public BarraDeProgreso getBarraProgreso(){
    	return this.p;
    }
    
    @Override
    public void run() {
         //To change body of generated methods, choose Tools | Templates.
         //p.setUndecorated(true);
         p.setVisible(true);
         p.getLabelProcesand().setVisible(true);
         p.getBarraProgreso().setIndeterminate(true);
        try{
            for(int i=0;i<11;i++){
                //p.getjTextArea1().append("Proceso en:"+i+"\n");
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
        	p.cerrar();
        	p.setVisible(false);
            
        } 
        catch(Exception e){
            System.out.println("error:"+e);
        }
        //tenemos que poner nuestro proceso
        
        p.getLabelProcesand().setVisible(false);
        p.getBarraProgreso().setIndeterminate(false);
    }
    
}
