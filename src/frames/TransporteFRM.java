package frames;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dtos.TransporteDTO;
import util.FondoInicial;
import util.Util;

public class TransporteFRM extends AbstractFRM{
	
	private JLabel labelUsuario;
	private JTextField textDisco;
	private JButton buttonBuscar;
	private JButton buttonEditar;
	private JButton buttonAgregar;
	private FondoInicial fondo;
	private JScrollPane scrollListaUsuario;
	private JTable tableTransporte;
	private AgregarTransporteFRM transporteTemplate;
	
	private static final long serialVersionUID = 6312166505526175828L;
	public TransporteFRM() throws SQLException{
		Util.loadProperties();
		initComponents();
		
	}
	public final void initComponents() throws SQLException{
    	setLayout(new BorderLayout());
        setTitle(".::TRANSPORTE");
    	setSize(480,380);
    	setIconImage(new ImageIcon(getClass().getResource("/imagenes/bus_grn.png")).getImage());
    	
    	labelUsuario= new JLabel();
    	textDisco= new JTextField();
    	buttonAgregar= new JButton();
    	buttonBuscar= new JButton();
    	buttonEditar= new JButton();
    	scrollListaUsuario= new JScrollPane();
    	tableTransporte=new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }}; 
            
            tableTransporte.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                	if(e.getClickCount()==2){
                		accionEditar();
                	}
                }
           });
    	fondo= new FondoInicial();
    	transporteTemplate = new AgregarTransporteFRM();
    	
    	textDisco.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){ 
					try {
						limpiarTabla(tableTransporte);
						accionBuscar();
						tableTransporte.getSelectionModel().setSelectionInterval(0,0);					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyTyped(KeyEvent e) {	
			}
		});
    	fondo.setBounds(0,0, 300,380);
    	fondo.setLayout(new FlowLayout());
    	fondo.add(labelUsuario);
    	fondo.add(textDisco);
    	add(fondo);
    	//transporteTemplate.setVisible(false);    	
    	labelUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bus.png")));
    	labelUsuario.setText("Disco:");
    	
    	
 //BUTTON BUSCAR
        buttonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/find_icon.png")));
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
 		   public void actionPerformed(java.awt.event.ActionEvent evt) {
 			  try {
 	 			   accionBuscar();
				} catch (SQLException e) {
					e.printStackTrace();
				}
 		    }
 		   });

        
// BUTTON EDITAR
        buttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit_icon.png")));
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
  		   public void actionPerformed(java.awt.event.ActionEvent evt) {
  			   accionEditar();
  		    }
        });

//BUTTON AGREGAR
        buttonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_icon.png")));
        buttonAgregar.addActionListener(new java.awt.event.ActionListener() {
  		   public void actionPerformed(java.awt.event.ActionEvent evt) {
  			 transporteTemplate.setAccion(false); 
  			   transporteTemplate.setVisible(true);
  		    }
        });
        
//TABLA        
        DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Estado");
        tableTransporte.setModel(modelo);
        scrollListaUsuario.setViewportView(tableTransporte);
        
        
    	
    	
    	
    	
    	
    	javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(textDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addComponent(scrollListaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setVisible(true);
        setLocationRelativeTo(null);
        //setVisible(true);
	
	}
    public void accionEditar() {
    	try {
            transporteTemplate.setAccion(true);    
    		Long disco=Long.parseLong(tableTransporte.getValueAt(tableTransporte.getSelectedRow(), 0)+"");
			TransporteDTO transporte=corporativo.obtenerTransportePorDisco(disco);
			transporteTemplate.getTextId().setText(transporte.getId()+"");
			transporteTemplate.getTextDisco().setText(transporte.getDisco()+"");
			transporteTemplate.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void accionBuscar() throws SQLException {
		String disco= new String();
		limpiarTabla(tableTransporte);
		disco=textDisco.getText();
        ArrayList <TransporteDTO> transporteLista= new ArrayList<>();
	    if(disco.isEmpty()){
		  transporteLista= corporativo.obtenerTransportes();
		  DefaultTableModel model = (DefaultTableModel) tableTransporte.getModel();
			for(int i=0;i<model.getRowCount();i++){
			  model.removeRow(i);
			}   
		    for(TransporteDTO trans:transporteLista){
		      model.addRow(new Object[]{trans.getDisco().toString(), "Disco # "+trans.getDisco().toString(), trans.getEstado()});
		    }
	    }
	    else{ 
	    	TransporteDTO user= new TransporteDTO();
	    	user=corporativo.obtenerTransportePorDisco(Long.parseLong(textDisco.getText()));
	    	if(user.getDisco() == null){
	    		JOptionPane.showMessageDialog(null, " No se encontro ningun TRANSPORTE  ", "info", JOptionPane.INFORMATION_MESSAGE);   
	    	}
			   else{
				 DefaultTableModel model = (DefaultTableModel) tableTransporte.getModel();
				 model.addRow(new Object[]{user.getDisco().toString(), user.getId().toString(), user.getEstado()});   
			   }
		   }
	}

	public static void limpiarTabla(JTable tabla){

		  try {
		      DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
		      int filas=tabla.getRowCount();
		      for (int i = 0;filas>i; i++) {
		          modelo.removeRow(0);
		      }
		  } catch (Exception e) {
		      JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
		  }
	}
	public static void main(String args[]) {
        try {
        	new TransporteFRM().setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}
