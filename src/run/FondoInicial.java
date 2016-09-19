/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class FondoInicial extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = -984618177605703506L;
	private Image fondo;
    
    public FondoInicial(){
    
        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo_gig4.jpg")).getImage();

    
    }
       @Override
    public void paint(Graphics g) {
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
        } else {
            setOpaque(true);
        }

        super.paint(g);
    }



}




