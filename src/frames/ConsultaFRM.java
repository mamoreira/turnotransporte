package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import dtos.TurnoDTO;
import util.FondoInicial;
import util.Label;

public class ConsultaFRM extends AbstractFRM{

	private Label labelFecha;
	private Label labelId;
	private JButton buttonBuscar;
    private JDateChooser chooserFechaInicial;
    private JTextField textId;
    private FondoInicial fondo; 
    private TurnoDTO turno;
    private PantallaCargando p;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6312166505526175828L;
     public ConsultaFRM (){
    	 turno=new TurnoDTO();
		initComponent();
	}

	private void initComponent() {
		setLayout(new BorderLayout());
        setTitle(".::CONSULTA TURNO");
    	setSize(400,150);
    	labelFecha = new Label("FECHA:");
    	labelId=new Label("ID: ");
    	textId=new JTextField(5);
    	buttonBuscar=new JButton();
    	
    	chooserFechaInicial= new JDateChooser();
    	fondo=new FondoInicial("/imagenes/fondomenu.jpg");
    	
    	
    	buttonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar_icon.png"))); // NOI18N
    	buttonBuscar.setFont(new java.awt.Font("Kristen ITC", 1, 14)); 
    	buttonBuscar.setFocusable(false);
    	buttonBuscar.setBackground(new Color(0, 204, 204));
    	buttonBuscar.setText("");
    	buttonBuscar.setMargin(new Insets(0, 0, 0, 0));
    	buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
		   public void actionPerformed(java.awt.event.ActionEvent evt) {
			
		    }
		    
		   }
    	
    	);
    	
    	buttonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
    			
            @Override
            public void mousePressed(MouseEvent e) {
            	p = new PantallaCargando();
            	p.setProgreso("Cargando...", 50);
            }
        });
    	fondo.setBounds(0,0, 300,250);
    	fondo.setLayout(new FlowLayout());
    	fondo.add(labelId);
    	fondo.add(textId);
    	fondo.add(labelFecha);
    	fondo.add(chooserFechaInicial);
    	fondo.add(buttonBuscar);
    	add(fondo,BorderLayout.CENTER);
    	setLocationRelativeTo(null);
        setVisible(true);
	}

	
	
	@SuppressWarnings("ResultOfObjectAllocationIgnored")
	protected void acciongenerar() throws SQLException {
		int resp1 = JOptionPane.showConfirmDialog(null, "Est� seguro que desea generar el tunro");
		if(resp1==0){
			TurnoDTO turnoTmp=corporativo.validarRangoFechasTurno(turno); // si trae dato es porq ya hay un turno para las fechas ingresadas
			if( turnoTmp== null){
		    	turno=corporativo.guardarTurno(turno);
		    	corporativo.generarMatrizTurno(turno);
		    	corporativo.mostarReporteTurno(turno);
		    	
			}else{
				turno= turnoTmp;
				int resp = JOptionPane.showConfirmDialog(null, "Se encontr� un sorteo generado para las fehcas establecidas Desea mostrarlo?");
				if(resp==0){
			    	corporativo.mostarReporteTurno(turno);
				}
			}
		}
		p.setVisible(false);
	}
}