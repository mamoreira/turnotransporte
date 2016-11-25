package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.tools.Tool;

import com.sun.javafx.tk.Toolkit;

import dtos.TurnoDTO;
import dtos.UsuarioDTO;
import util.FondoInicial;
import util.Util;

public class UsuarioFRM extends AbstractFRM{
	
	private JLabel labelUsuario;
	private JTextField textUsuario;
	private JButton buttonBuscar;
	private JButton buttonEditar;
	private JButton buttonAgregar;
	private FondoInicial fondo;
	private JScrollPane scrollListaUsuario;
	private JTable tableUsuario;
	private AgregarUsuarioFRM usuarioTemplate;
	
	private static final long serialVersionUID = 6312166505526175828L;
	public UsuarioFRM() throws SQLException{
		Util.loadProperties();
		initComponents();
		
	}
	public final void initComponents() throws SQLException{
    	setLayout(new BorderLayout());
        setTitle(".::AUTENTICACION");
    	setSize(480,380);
    	setIconImage(new ImageIcon(getClass().getResource("/imagenes/bus_grn.png")).getImage());
    	
    	labelUsuario= new JLabel();
    	textUsuario= new JTextField();
    	buttonAgregar= new JButton();
    	buttonBuscar= new JButton();
    	buttonEditar= new JButton();
    	scrollListaUsuario= new JScrollPane();
    	tableUsuario= new JTable();
    	fondo= new FondoInicial();
    	usuarioTemplate = new AgregarUsuarioFRM();
    	
    	fondo.setBounds(0,0, 300,380);
    	fondo.setLayout(new FlowLayout());
    	fondo.add(labelUsuario);
    	fondo.add(textUsuario);
    	add(fondo);
    	
    	usuarioTemplate.setVisible(false);
    	
    	
    	labelUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png")));
    	labelUsuario.setText("Usuario:");
    	
    	
 //BUTTON BUSCAR
        buttonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/find_icon.png")));

        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
 		   public void actionPerformed(java.awt.event.ActionEvent evt) {
 			  try {
 	 			   String usuario= new String();
 	 			   usuario=textUsuario.getText();
 	 			   ArrayList <UsuarioDTO> usuariosLista= new ArrayList<>();
 	 			   if(usuario.isEmpty()){
 	 				   usuariosLista= corporativo.getUsuarios();
 	 				   DefaultTableModel model = (DefaultTableModel) tableUsuario.getModel();
 	 				   for(int i=0;i<model.getRowCount();i++){
 	 					 model.removeRow(i);
 	 				   }
 	 				   
 	 	 			   for(UsuarioDTO user:usuariosLista){
 	 	 	 			 model.addRow(new Object[]{user.getNombre().toString(), user.getId().toString(), user.getEstado()});
 	 	 			   }
 	 			   }
 	 			   else{ 
 	 				   UsuarioDTO user= new UsuarioDTO();
 	 				   user=corporativo.getUsuarioPorNombre(usuario);
 	 				   if(user.getNombre().isEmpty()){
 	 					 JOptionPane.showMessageDialog(null, " No se encontro ningun USUARIO  ", "error", JOptionPane.ERROR_MESSAGE);   
 	 				   }
 	 				   else{
 	 					 DefaultTableModel model = (DefaultTableModel) tableUsuario.getModel();
 	 					 model.addRow(new Object[]{user.getNombre().toString(), user.getId().toString(), user.getEstado()});
 	 					   
 	 				   }
 	 				   
 	 				   
 	 			   }
 	 			   
				} catch (SQLException e) {
					e.printStackTrace();
				}
 		    }
 		   });

        
// BUTTON EDITAR
        buttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit_icon.png")));
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
  		   public void actionPerformed(java.awt.event.ActionEvent evt) {
  			   //System.exit(0);
  			   //BORRAR DATOS OCULTAR
  		    }
        });

//BUTTON AGREGAR
        buttonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_icon.png")));
        buttonAgregar.addActionListener(new java.awt.event.ActionListener() {
  		   public void actionPerformed(java.awt.event.ActionEvent evt) {
  			   usuarioTemplate.setVisible(true);
  			   setVisible(false);
  		    }
        });
        
//TABLA        
        DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Estado");
        tableUsuario.setModel(modelo);
        scrollListaUsuario.setViewportView(tableUsuario);
        
        
    	
    	
    	
    	
    	
    	javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(scrollListaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonEditar)
                    .addComponent(buttonBuscar)
                    .addComponent(buttonAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addComponent(scrollListaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(0, 150, Short.MAX_VALUE))
//        );
        setVisible(true);
        setLocationRelativeTo(null);
        //setVisible(true);
	
	}
	

	
	public static void main(String args[]) {
        try {
        	new UsuarioFRM().setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}
