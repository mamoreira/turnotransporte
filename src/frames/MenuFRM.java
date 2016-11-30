package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import util.FondoInicial;
import util.Util;

public class MenuFRM extends AbstractFRM{

	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6704917066251864499L;

    
    //Variables
    private FondoInicial fondo; 
    private JButton buttonUsuario;
    private JButton buttonGenerarTurno;
    private JButton buttonConsultarTurno;
    private JButton buttonTransporte;
   // public static HiloProgreso hiloProgreso = new HiloProgreso();
    
	
	public MenuFRM() throws SQLException{
		Util.loadProperties();
		initComponents();
	}
	public final void initComponents() throws SQLException{
    	setLayout(new BorderLayout());
    	setIconImage(new ImageIcon(getClass().getResource("/imagenes/bus_grn.png")).getImage());
        setTitle(".::MENU PRINCIPAL");
    	setSize(300,380);
    	setIconImage(new ImageIcon(getClass().getResource("/imagenes/bus_grn.png")).getImage());
    	
    	buttonUsuario= new JButton();
    	buttonGenerarTurno= new JButton();
    	buttonConsultarTurno= new JButton();
    	buttonTransporte= new JButton();
    	fondo=new FondoInicial("/imagenes/fondomenu.jpg");

    	
    	buttonGenerarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/generarTurno.png"))); // NOI18N
    	buttonGenerarTurno.setFont(new java.awt.Font("Kristen ITC", 1, 14)); 
    	buttonGenerarTurno.setFocusable(false);
    	buttonGenerarTurno.setBackground(new Color(0, 204, 204));
    	buttonGenerarTurno.setText(" Generar Sorteo  ");
    	buttonGenerarTurno.addActionListener(new java.awt.event.ActionListener() {
		   public void actionPerformed(java.awt.event.ActionEvent evt) {
		        new MatrizFRM().setVisible(true);
		    }
		   });
    	buttonConsultarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menuBuscar.png"))); // NOI18N
    	buttonConsultarTurno.setFont(new java.awt.Font("Kristen ITC", 1, 14)); 
    	buttonConsultarTurno.setFocusable(false);
    	buttonConsultarTurno.setBackground(new Color(0, 204, 204));
    	buttonConsultarTurno.setText("Consultar Turnos");
    	buttonConsultarTurno.addActionListener(new java.awt.event.ActionListener() {
		   public void actionPerformed(java.awt.event.ActionEvent evt) {
			   new ConsultaFRM().setVisible(true);
		    }
		   });
    	
    	buttonTransporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/transporte.png"))); // NOI18N
    	buttonTransporte.setFont(new java.awt.Font("Kristen ITC", 1, 14)); 
    	buttonTransporte.setFocusable(false);
    	buttonTransporte.setBackground(new Color(0, 204, 204));
    	buttonTransporte.setText("    Transporte      ");
    	buttonTransporte.addActionListener(new java.awt.event.ActionListener() {
		   public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					new TransporteFRM().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		   });
    	
    	buttonUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
    	buttonUsuario.setFont(new java.awt.Font("Kristen ITC", 1, 14)); 
    	buttonUsuario.setFocusable(false);
    	buttonUsuario.setBackground(new Color(0, 204, 204));   
    	buttonUsuario.setText("Usuario/Persona ");
    	buttonUsuario.addActionListener(new java.awt.event.ActionListener() {
		   public void actionPerformed(java.awt.event.ActionEvent evt) {
			   try {
				new UsuarioFRM().setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
		   });
    	fondo.setBounds(0,0, 300,380);
    	fondo.setLayout(new FlowLayout());
    	fondo.add(buttonGenerarTurno);
    	fondo.add(buttonConsultarTurno);
    	fondo.add(buttonTransporte);
    	fondo.add(buttonUsuario);
    	add(fondo);

    	setLocationRelativeTo(null);
        setVisible(true);

    }
	 protected void accionUsuario() {
		// TODO Auto-generated method stub
		
	}
	public static void main(String args[]) {
	        try {
				new MenuFRM().setVisible(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }

}
