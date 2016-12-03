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

import dtos.UsuarioDTO;
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
	private boolean accion;// false giardar true actualizar
	
	private FondoInicial fondo;
    private JButton buttonAgregar;

    
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

    	
        labelId.setText("ID:");
        labelUsuario.setText("USUARIO:");
        labelClave.setText("CONTRASENA:");    
        labelEstado.setText("ESTADO:");
        cmbBxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        
// BOTON AGREGAR
        buttonAgregar.setText("GUARDAR");
        buttonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/find_icon.png")));
        buttonAgregar.addActionListener(new java.awt.event.ActionListener() {
 		   public void actionPerformed(java.awt.event.ActionEvent evt) {
 			    try {
 			    	if(validarCampos()){
 			    		accionGuardar();
 					}
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 		    }
 		    
 		   }
     	
     	);


        javax.swing.GroupLayout panelLogin = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(panelLogin);
    	panelLogin.setHorizontalGroup(
                panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLogin.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelLogin.createSequentialGroup()
                        	.addGap(7, 7, 7)
                        	.addComponent(buttonAgregar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                            )
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
                .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(buttonAgregar))
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
	 //0 guardar 1 actualizar
	 protected void accionGuardar() throws SQLException {
		 UsuarioDTO usuario=new UsuarioDTO();
		 usuario.setId(Long.parseLong(textId.getText()));
		 usuario.setNombre(textUsuario.getText());
		 usuario.setClave(textPass.getText());
		 usuario.setEstado(cmbBxEstado.getSelectedItem()=="Activo"?"A":"I");
		 if(accion){
	        corporativo.actualizarUsuario(usuario);
		 }else{
		    corporativo.guardarUsuario(usuario);
		 }
		 JOptionPane.showMessageDialog(null, "Usuario Guardó correctamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
		 setVisible(false);
	     
		}
	 
	 
	 
	public static void main(String args[]) {
	        try {
	        	new AgregarUsuarioFRM().setVisible(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public JTextField getTextId() {
		return textId;
	}
	public void setTextId(JTextField textId) {
		this.textId = textId;
	}
	public JTextField getTextUsuario() {
		return textUsuario;
	}
	public void setTextUsuario(JTextField textUsuario) {
		this.textUsuario = textUsuario;
	}
	public JPasswordField getTextPass() {
		return textPass;
	}
	public void setTextPass(JPasswordField textPass) {
		this.textPass = textPass;
	}
	public JComboBox<String> getCmbBxEstado() {
		return cmbBxEstado;
	}
	public void setCmbBxEstado(JComboBox<String> cmbBxEstado) {
		this.cmbBxEstado = cmbBxEstado;
	}
	public boolean isAccion() {
		return accion;
	}
	public void setAccion(boolean accion) {
		this.accion = accion;
	}
	
	
}
