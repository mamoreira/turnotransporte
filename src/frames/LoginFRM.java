package frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.FondoInicial;
import util.Util;

public class LoginFRM extends AbstractFRM{

	private static final long serialVersionUID = 6312166505526175828L;
	
	private JLabel labelUsuario;
	private JLabel labelContrasena;
	private JTextField textUser;
	private JPasswordField textPass;
	
	private FondoInicial fondo;
    private JButton buttonIngresar;
    private JButton buttonCancelar;
    private JButton buttonConsultarTurno;
    private JButton buttonTransporte;
    
    private PantallaCargando p;
	
	public LoginFRM() throws SQLException{
		Util.loadProperties();
		initComponents();
		
	}
	public final void initComponents() throws SQLException{
    	setLayout(new BorderLayout());
        setTitle(".::AUTENTICACION");
    	setSize(300,380);
    	setIconImage(new ImageIcon(getClass().getResource("/imagenes/bus_grn.png")).getImage());
    	
    	
    	buttonIngresar= new JButton();
    	buttonCancelar= new JButton();
    	labelUsuario= new JLabel();
    	labelContrasena= new JLabel();
    	textUser= new JTextField();
    	textPass= new JPasswordField();
    	
    	fondo=new FondoInicial("/imagenes/fondomenu.jpg");

    	
//    	buttonGenerarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/generarTurno.png"))); // NOI18N
//    	buttonGenerarTurno.setFont(new java.awt.Font("Kristen ITC", 1, 14)); 
//    	buttonGenerarTurno.setFocusable(false);
//    	buttonGenerarTurno.setBackground(new Color(0, 204, 204));
//    	buttonGenerarTurno.setText(" Generar Sorteo  ");
//    	buttonGenerarTurno.addActionListener(new java.awt.event.ActionListener() {
//		   public void actionPerformed(java.awt.event.ActionEvent evt) {
//		        new MatrizFRM().setVisible(true);
//		    }
//		   });
//    	buttonConsultarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menuBuscar.png"))); // NOI18N
//    	buttonConsultarTurno.setFont(new java.awt.Font("Kristen ITC", 1, 14)); 
//    	buttonConsultarTurno.setFocusable(false);
//    	buttonConsultarTurno.setBackground(new Color(0, 204, 204));
//    	buttonConsultarTurno.setText("Consultar Turnos");
//    	buttonConsultarTurno.addActionListener(new java.awt.event.ActionListener() {
//		   public void actionPerformed(java.awt.event.ActionEvent evt) {
//			   new ConsultaFRM().setVisible(true);
//		    }
//		   });
    	
    	

    	fondo.setBounds(0,0, 300,380);
    	fondo.setLayout(new FlowLayout());
    	fondo.add(labelUsuario);
    	fondo.add(labelContrasena);
    	add(fondo);

    	
    	labelUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png"))); // NOI18N
        labelUsuario.setText("Usuario:");

        buttonIngresar.setText("Ingresar");
//        buttonIngresar.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jToggleButton1ActionPerformed(evt);
//            }
//        });
        buttonIngresar.addActionListener(new java.awt.event.ActionListener() {
 		   public void actionPerformed(java.awt.event.ActionEvent evt) {
 			    try {
 			    	if(validarCampos()){
 			    		//p = new PantallaCargando();
 		            	//p.setProgreso("Cargando...", 50);
 			    		acciongenerar();
 					}
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 		    }
 		    
 		   }
     	
     	);

        labelContrasena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lock.png"))); // NOI18N
        labelContrasena.setText("Contrasena:");

        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
  		   public void actionPerformed(java.awt.event.ActionEvent evt) {
  			   System.exit(0);
  		    }
        });
//        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton1ActionPerformed(evt);
//            }
//        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(buttonIngresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelUsuario)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelContrasena)
                                .addGap(13, 13, 13)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textPass, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                            .addComponent(textUser))))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelContrasena)
                    .addComponent(textPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonIngresar)
                    .addComponent(buttonCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


    	
    	
    	
    	
   	setLocationRelativeTo(null);
        setVisible(true);

    }
	
	 protected void accionUsuario() {
		// TODO Auto-generated method stub
		
	}
	 
	 protected boolean validarCampos() {
			if (textUser.getText() == null){
				JOptionPane.showMessageDialog(null, "Ingrese el NOMBRE DE USUARIO ", "error", JOptionPane.ERROR_MESSAGE);
				return false;
			}else {
				if(textPass.getPassword() == null){
				JOptionPane.showMessageDialog(null, "Ingrese CONTRASENA", "error", JOptionPane.ERROR_MESSAGE);
				return false;
				}
				else
					return true;
			}
					
	}
	 
	 protected void acciongenerar() throws SQLException {
				Boolean llave=corporativo.validarLogin(textUser.getText(),textPass.getText()); 
				if(llave==true){
					this.setVisible(false);
					new MenuFRM().setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null, "USUARIO o CONTRASENA incorrecto ", "error", JOptionPane.ERROR_MESSAGE);
					textPass.setText(null);
				}
			    	
		}
	 
	 
	 
	public static void main(String args[]) {
	        try {
	        	new LoginFRM().setVisible(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
}
