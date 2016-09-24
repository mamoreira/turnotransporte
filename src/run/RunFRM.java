package run;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import com.toedter.calendar.JDateChooser;

import dtos.TurnoDTO;

public class RunFRM extends AbstractFRM{

	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6704917066251864499L;
	private FondoInicial fondo;
	private javax.swing.JPanel panelBotones;
	private javax.swing.JPanel panelCabecera;
	private javax.swing.JPanel panelDetalle;
	private javax.swing.JPanel panelBody;
	private javax.swing.JPanel panelContenedor;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JButton buttonProcesar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelFechaCreacion;
    private javax.swing.JLabel labelFechaInicial;
    private javax.swing.JLabel labelFechaFinal;
    private JDateChooser chooserFechaInicial;
    private JDateChooser chooserFechaFinal;
    private javax.swing.JTextField textUsuario;
    private javax.swing.JTextField textFechaCreacion;
    
    //Variables
    private TurnoDTO turno;
	public RunFRM() throws SQLException{
		turno = new TurnoDTO();
		turno.setId(1L);
		corporativo.generarMatrizTurno(turno);
		initComponents();
	}
	public final void initComponents() throws SQLException{
    	setLayout(new BorderLayout());
        setTitle(".::ASIGNAR TURNO");
        Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize(); 
    	setSize((pantallaTamano.width-100), (pantallaTamano.height-100)); 
        panelBotones = new javax.swing.JPanel();
        panelContenedor=new javax.swing.JPanel();
        panelCabecera=new javax.swing.JPanel();
        panelDetalle=new javax.swing.JPanel();
        panelBody=new javax.swing.JPanel();
        scrollPanel=new JScrollPane();
        fondo=new FondoInicial();
    	buttonProcesar = new javax.swing.JButton();
    	buttonGuardar= new javax.swing.JButton();
    	labelUsuario= new javax.swing.JLabel("Usuario: ");
    	labelFechaCreacion= new javax.swing.JLabel("Fecha Creacion: ");
    	labelFechaInicial= new javax.swing.JLabel("Fecha Inicial: ");
    	labelFechaFinal= new javax.swing.JLabel("Fecha Final: ");
    	chooserFechaInicial= new JDateChooser();
    	chooserFechaFinal= new JDateChooser();
    	textUsuario = new javax.swing.JTextField(10);
    	textFechaCreacion= new javax.swing.JTextField(10);
    	
    	textUsuario.setText("vvillacres");
    	textUsuario.setEnabled(false);
    	textFechaCreacion.setText(corporativo.getFecha());
    	textFechaCreacion.setEnabled(false);
    	buttonProcesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/process_icon.png"))); // NOI18N
		buttonProcesar.setText("Procesar");
		buttonProcesar.addActionListener(new java.awt.event.ActionListener() {
		   public void actionPerformed(java.awt.event.ActionEvent evt) {
		        try {
		       	 accionProcesar();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		   });
		buttonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/process_icon.png"))); // NOI18N
		buttonGuardar.setText("Guardar");
		buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
		   public void actionPerformed(java.awt.event.ActionEvent evt) {
		        try {
		       	 accionGuardar();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		   });
		
        panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBotones.setPreferredSize(new Dimension(100,60));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Acciones"));
		panelBotones.add(buttonGuardar);
		panelBotones.add(buttonProcesar);
 
        panelCabecera.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelCabecera.setPreferredSize(new Dimension(100,60));
        panelCabecera.setBorder(BorderFactory.createTitledBorder("Turno"));
		panelCabecera.add(labelUsuario);
		panelCabecera.add(textUsuario);
		panelCabecera.add(labelFechaCreacion);
		panelCabecera.add(textFechaCreacion);
		panelCabecera.add(labelFechaInicial);
		panelCabecera.add(chooserFechaInicial);
		panelCabecera.add(labelFechaFinal);
		panelCabecera.add(chooserFechaFinal);	
		
		panelBody.setLayout(new BorderLayout()); 
		panelBody.add(panelCabecera,BorderLayout.NORTH);
    	panelContenedor.setLayout(new BorderLayout()); 
        panelContenedor.add(panelBotones,BorderLayout.NORTH);
        panelContenedor.add(panelBody, BorderLayout.CENTER);
        
        scrollPanel.setViewportView(panelContenedor);

        add(panelContenedor);
        setLocationRelativeTo(null);
        

    }

    protected void accionGuardar()throws SQLException {
//    	turno.setFechaCreacion(new Date(textFechaCreacion.getText()));
    	turno.setFechaInicio(chooserFechaInicial.getDate());
    	turno.setFechaFin(chooserFechaFinal.getDate());
    	corporativo.guardarTurno(turno);
		
	}
	protected void accionProcesar()  throws SQLException{
    	corporativo.generarMatrizTurno(turno);
	}
	/**
	 * PROGRAMA PRINCIPAL
	 * @param args
	 */
    public static void main(String args[]) {
        try {
			new RunFRM().setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

//	public static void main(String[] args) {
//		CorporativoDAO corporativo= new CorporativoDAOImpl();
//		try {
//			PersonaDTO p=corporativo.obtenerPersonaPorId(1L);
//			System.out.println(p.getId()+ " "+ p.getNombre());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	
//	}

}
