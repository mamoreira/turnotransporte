package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.generic.LADD;
import com.toedter.calendar.JDateChooser;

import dtos.TurnoDTO;
import util.FondoInicial;
import util.Label;
import util.Util;

public class MatrizFRM extends AbstractFRM{

	private Label labelFechaInicial;
	private Label labelFechaFinal;
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
    	labelFechaFinal = new Label("Fecha Final *");
    	labelFechaInicial=new Label("Fecha Inicial *");
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
			    	if(validarCampos()){
			    		acciongenerar();
					}
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

	protected boolean validarCampos() {
		if (chooserFechaInicial.getDate() == null){
			JOptionPane.showMessageDialog(null, "Campo FECHA INICIAL obligatorio", "error", JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(chooserFechaFinal.getDate() == null){
				JOptionPane.showMessageDialog(null, "Campo FECHA FINAL obligatorio", "error", JOptionPane.ERROR_MESSAGE);
				return false;
		}else if(Util.getDiaSemana(chooserFechaInicial.getDate())!=2){
			JOptionPane.showMessageDialog(null, "Fecha inicial incorrecta, acepta solo LUNES", "error", JOptionPane.ERROR_MESSAGE);
			return false;
	    }else if(Util.getDiaSemana(chooserFechaFinal.getDate())!=1){
			JOptionPane.showMessageDialog(null, "Fecha final incorrecta, acepta solo DOMINGOS", "error", JOptionPane.ERROR_MESSAGE);
			return false;
	    }else if(Util.diferenciaFechas(chooserFechaInicial.getDate(),chooserFechaFinal.getDate())!=7){
			JOptionPane.showMessageDialog(null, "Se puede generar sorteo solo por semana", "error", JOptionPane.ERROR_MESSAGE);
			return false;
	    }
				
		turno.setFechaInicio(chooserFechaInicial.getDate());
    	turno.setFechaFin(chooserFechaFinal.getDate());    	
    	turno.setFechaCreacion(new Date(corporativo.getFecha()));
		return true;
	}
	
	@SuppressWarnings("ResultOfObjectAllocationIgnored")
	protected void acciongenerar() throws SQLException {
		MenuFRM.hiloProgreso.iniciar();
		TurnoDTO turnoTmp=corporativo.validarRangoFechasTurno(turno); // si trae dato es porq ya hay un turno para las fechas ingresadas
		if( turnoTmp== null){
	    	turno=corporativo.guardarTurno(turno);
	    	corporativo.generarMatrizTurno(turno);
	    	corporativo.mostarReporteTurno(turno);
	    	
		}else{
			turno= turnoTmp;
			int resp = JOptionPane.showConfirmDialog(null, "Se encontró un sorteo generado para las fehcas establecidas Desea mostrarlo?");
			if(resp==0){
		    	corporativo.mostarReporteTurno(turno);
			}
		}
		MenuFRM.hiloProgreso.detener();
	}
}
