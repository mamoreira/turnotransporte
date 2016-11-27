package frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.FondoInicial;
import util.Util;

public class AgregarUsuarioFRM extends AbstractFRM{

	private static final long serialVersionUID = 6312166505526175828L;
	
	private JLabel labelId;
	private JLabel labelUsuario;
	private JLabel labelClave;
	private JLabel labelEstado;
	private JTextField textId;
	private JTextField textUsuario;
	private JPasswordField textPass;
	private JComboBox<String> cmbBxEstado;
	
	private FondoInicial fondo;
    private JButton buttonAgregar;
    private JButton buttonCancelar;

    
    private PantallaCargando p;
	
	public AgregarUsuarioFRM() throws SQLException{
		Util.loadProperties();
		initComponents();
		
	}
	public final void initComponents() throws SQLException{
    	setLayout(new BorderLayout());
        setTitle(".::AUTENTICACION");
    	setSize(300,380);
    	setIconImage(new ImageIcon(getClass().getResource("/imagenes/bus_grn.png")).getImage());
    	
    	//Botones
    	buttonAgregar= new JButton();
    	buttonCancelar= new JButton();
    	
    	
    	labelId= new JLabel();
    	labelUsuario= new JLabel();
    	labelClave = new JLabel();
    	labelEstado = new JLabel();
    	
    	
    	textId= new JTextField();
    	textUsuario= new JTextField();
    	textPass= new JPasswordField();
    	
    	cmbBxEstado = new JComboBox<>();
    	
    	
    	
    	
    	fondo=new FondoInicial("/imagenes/fondomenu.jpg");
    	
    	

    	fondo.setBounds(0,0, 300,380);
    	fondo.setLayout(new FlowLayout());
    	fondo.add(labelId);
    	fondo.add(labelUsuario);
    	fondo.add(labelClave);
    	fondo.add(labelEstado);
    	fondo.add(cmbBxEstado);
    	add(fondo);

    	
    	labelId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png"))); // NOI18N
        labelId.setText("ID:");
        
        labelUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lock.png"))); // NOI18N
        labelUsuario.setText("USUARIO:");
        
        labelClave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lock.png"))); // NOI18N
        labelClave.setText("CONTRASENA:");
        
        labelEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lock.png"))); // NOI18N
        labelEstado.setText("ESTADO:");
        
        cmbBxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        
// BOTON AGREGAR
        buttonAgregar.setText("Agregar");
//        buttonIngresar.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jToggleButton1ActionPerformed(evt);
//            }
//        });
        buttonAgregar.addActionListener(new java.awt.event.ActionListener() {
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

        
// BOTON CANCELAR
        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
  		   public void actionPerformed(java.awt.event.ActionEvent evt) {
  			   //System.exit(0);
  			   //BORRAR DATOS OCULTAR
  		    }
        });


        javax.swing.GroupLayout panelLogin = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(panelLogin);
    	//new AgregarUsuarioFRM().setVisible(true);
    	panelLogin.setHorizontalGroup(
                panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLogin.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelLogin.createSequentialGroup()
                        	.addGap(7, 7, 7)
                        	.addComponent(buttonAgregar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                            .addComponent(buttonCancelar))
                        .addGroup(panelLogin.createSequentialGroup()
                            .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelId)
                                .addComponent(labelUsuario)
                                .addComponent(labelClave)
                                .addComponent(labelEstado))
                            .addGap(22,22,22)
                            .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textPass, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                .addComponent(textUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                .addComponent(textId)
                                .addComponent(cmbBxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap(147, Short.MAX_VALUE))
            );
    	
    	panelLogin.setVerticalGroup(
            panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogin.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEstado))
                .addGap(18, 18, 18)
                .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAgregar)
                    .addComponent(buttonCancelar))
                .addContainerGap(107, Short.MAX_VALUE))
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
			if (textId.getText() == null){
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
				Boolean llave=corporativo.validarLogin(textId.getText(),textPass.getText()); 
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
	        	new AgregarUsuarioFRM().setVisible(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
}
