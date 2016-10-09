package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
 
public final class PantallaCargando extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 924583935739167731L;
	
	BorderLayout Layout = new BorderLayout();
    JPanel PanelInferior = new JPanel();
    FlowLayout PanelFlowLayout = new FlowLayout();
    JProgressBar Barra = new JProgressBar();
 
    public PantallaCargando() {
    	setUndecorated(true);
        dibujaVentana();
        setLocationRelativeTo(null);
        setProgresoMax(100);
        setVisible(true);
        velocidadDeCarga(999999);
    }
 
    public void dibujaVentana() {
        setLayout(Layout);
        PanelInferior.setLayout(PanelFlowLayout);
        PanelInferior.setBackground(Color.BLACK);
        add(PanelInferior, BorderLayout.SOUTH);
        PanelInferior.add(Barra, null);
        pack();
    }
 
    public void setProgresoMax(int maxProgress){
        Barra.setMaximum(maxProgress);
    }
 
    public void setProgreso(int progress) {
        final int progreso = progress;
        Barra.setValue(progreso);
    }
 
    public void setProgreso(String mensaje, int progress) {
        final int progreso = progress;
        final String elMensaje = mensaje;
        setProgreso(progress);
        Barra.setValue(progreso);
        setMensaje(elMensaje);
    }
 
    private void setMensaje(String mensaje) {
        if (mensaje==null){
            mensaje = "";
            Barra.setStringPainted(false);
        } else{
            Barra.setStringPainted(true);
        } Barra.setString(mensaje);
    }
 
    public void velocidadDeCarga(int tiempo) {
        for (int i = 0; i <= 100; i++) {
            for (long j=0; j < tiempo; ++j) {
                String barra = " " + (j + i);
            }
            setProgreso("Cargando " + i, i);
        }
        dispose();
    }
     
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
    	PantallaCargando p=new PantallaCargando();
    }
}