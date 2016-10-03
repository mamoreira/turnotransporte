package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import dtos.TurnoDTO;

public class MatrizFRM extends AbstractFRM{

	private JLabel labelFechaInicial;
	private JLabel labelFechaFinal;
	private JButton buttonGenerar;
    private JDateChooser chooserFechaInicial;
    private JDateChooser chooserFechaFinal;
    private FondoInicial fondo; 
    private TurnoDTO turno;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6312166505526175828L;
     public MatrizFRM (){
    	 turno=new TurnoDTO();
		initComponent();
	}

	private void initComponent() {
		setLayout(new BorderLayout());
        setTitle(".::NUEVO TURNO");
    	setSize(400,150);
    	
    	labelFechaFinal = new JLabel("Fecha Inicial");
    	labelFechaInicial=new JLabel("Fecha Final");
    	buttonGenerar=new JButton();
    	chooserFechaInicial= new JDateChooser();
    	chooserFechaFinal= new JDateChooser();
    	fondo=new FondoInicial("/imagenes/fondomenu.jpg");
    	
    	
    	buttonGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/process_icon.png"))); // NOI18N
    	buttonGenerar.setFont(new java.awt.Font("Kristen ITC", 1, 14)); 
    	buttonGenerar.setFocusable(false);
    	buttonGenerar.setBackground(new Color(0, 204, 204));
    	buttonGenerar.setText("Generar");
    	buttonGenerar.addActionListener(new java.awt.event.ActionListener() {
		   public void actionPerformed(java.awt.event.ActionEvent evt) {
			    try {
					acciongenerar();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		   });
    	
    	fondo.setBounds(0,0, 300,250);
    	fondo.setLayout(new FlowLayout());
    	fondo.add(labelFechaInicial);
    	fondo.add(chooserFechaInicial);
    	fondo.add(labelFechaFinal);
    	fondo.add(chooserFechaFinal);
    	add(fondo,BorderLayout.CENTER);
    	add(buttonGenerar,BorderLayout.SOUTH);
    	setLocationRelativeTo(null);
        setVisible(true);
	}

	protected void acciongenerar() throws SQLException {
		System.out.println(chooserFechaInicial.getDate());
    	turno.setFechaInicio(chooserFechaInicial.getDate());
    	turno.setFechaFin(chooserFechaFinal.getDate());    	
    	turno.setFechaCreacion(new Date(corporativo.getFecha()));
    	corporativo.guardarTurno(turno);
    	corporativo.generarMatrizTurno(turno);
		
	}
}
