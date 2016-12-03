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

import dtos.TransporteDTO;
import util.FondoInicial;
import util.Util;

public class AgregarTransporteFRM extends AbstractFRM{

	private static final long serialVersionUID = 6312166505526175828L;
	
	private JLabel labelId;
	private JLabel labelDisco;
	private JLabel labelEstado;
	private JTextField textId;
	private JTextField textDisco;
	private JComboBox<String> cmbBxEstado;
	private boolean accion;// false giardar true actualizar
	
	private FondoInicial fondo;
    private JButton buttonAgregar;

    
    private PantallaCargando p;
	
	public AgregarTransporteFRM() throws SQLException{
		Util.loadProperties();
		initComponents();
		
	}
	public final void initComponents() throws SQLException{
    	setLayout(new BorderLayout());
        setTitle(".::TRANSPORTE");
    	setSize(300,380);
    	setIconImage(new ImageIcon(getClass().getResource("/imagenes/bus_grn.png")).getImage());
    	
    	//Botones
    	buttonAgregar= new JButton();    	
    	
    	labelId= new JLabel();
    	labelDisco= new JLabel();
    	labelEstado = new JLabel();
    	textId= new JTextField();
    	textDisco= new JTextField();
    	cmbBxEstado = new JComboBox<>();
    	fondo=new FondoInicial("/imagenes/fondomenu.jpg");
    	fondo.setBounds(0,0, 300,380);
    	fondo.setLayout(new FlowLayout());
    	fondo.add(labelId);
    	fondo.add(labelDisco);
    	fondo.add(labelEstado);
    	fondo.add(labelEstado);
    	fondo.add(cmbBxEstado);
    	add(fondo); 	
        labelId.setText("ID:");
        labelDisco.setText("DISCO:");
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
                                .addComponent(labelDisco)
                                .addComponent(labelEstado)
                                .addComponent(labelEstado))
                            .addGap(22,22,22)
                            .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textDisco, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
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
                    .addComponent(labelDisco)
                    .addComponent(textDisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLogin.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                	)
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
	
	 protected void acciontransporte() {
		// TODO Auto-generated method stub
		
	}
	 
	 protected boolean validarCampos() {

					return false;
	}
	 //0 guardar 1 actualizar
	 protected void accionGuardar() throws SQLException {
		 TransporteDTO transporte=new TransporteDTO();
		 transporte.setId(Long.parseLong(textId.getText()));
		 transporte.setDisco(Long.parseLong(textDisco.getText()));
		 transporte.setEstado(cmbBxEstado.getSelectedItem()=="Activo"?"A":"I");
		 if(accion){
	        corporativo.actualizarTransporte(transporte);
		 }else{
		    corporativo.guardarTransporte(transporte);
		 }
		 JOptionPane.showMessageDialog(null, "transporte Guardó correctamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
		 setVisible(false);
	     
		}
	 
	 
	 
	public static void main(String args[]) {
	        try {
	        	new AgregarTransporteFRM().setVisible(true);
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
	public JTextField getTextDisco() {
		return textDisco;
	}
	public void setTextDisco(JTextField texttransporte) {
		this.textDisco = texttransporte;
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
