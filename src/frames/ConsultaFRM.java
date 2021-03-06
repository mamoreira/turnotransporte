package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sun.javafx.sg.prism.GrowableDataBuffer;
import com.toedter.calendar.JDateChooser;

import dtos.TurnoDTO;
import util.FondoInicial;
import util.Label;
import util.Util;

public class ConsultaFRM extends AbstractFRM{

	private Label labelDescription;
	private Label labelFechaFinal;
	private Label labelFechaInicial;
	private JButton buttonBuscar;
    private JDateChooser chooserFechaInicial;
    private JDateChooser chooserFechaFinal;
    private FondoInicial fondo; 
    private TurnoDTO turno;
    private TurnoDTO turnov;
    private PantallaCargando p;
    private JTable tableTurnos;
    private JScrollPane scrollListaTurnos; 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6312166505526175828L;
     public ConsultaFRM (){
    	 turno=new TurnoDTO();
    	 turnov= new TurnoDTO();
		initComponent();
	}

	private void initComponent() {
		setLayout(new BorderLayout());
        setTitle(".::CONSULTA TURNO");
    	setSize(500,250);
    	labelDescription= new Label("    Debe Escojer un Rango de Fecha No mayor a 30 Dias:   ");
    	labelFechaFinal = new Label("FECHA FINAL: ");
    	labelFechaInicial=new Label("FECHA INICIAL: ");
    	buttonBuscar=new JButton();
    	
    	chooserFechaInicial= new JDateChooser();
    	chooserFechaFinal = new JDateChooser();
    	tableTurnos =new JTable();
    	scrollListaTurnos= new JScrollPane();
    	
    	fondo=new FondoInicial("/imagenes/fondomenu.jpg");
    	
    	
    	buttonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar_icon.png"))); // NOI18N
    	buttonBuscar.setFont(new java.awt.Font("Kristen ITC", 1, 14)); 
    	buttonBuscar.setFocusable(false);
    	buttonBuscar.setBackground(new Color(0, 204, 204));
    	buttonBuscar.setText("");
    	buttonBuscar.setMargin(new Insets(0, 0, 0, 0));
    	buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
		   public void actionPerformed(java.awt.event.ActionEvent evt) {
			   try {
				   if(validarCampos()){
					   TurnoDTO turnoTmp= corporativo.validarRangoFechasTurno(turnov);
					   if(turnoTmp==null){
						   JOptionPane.showMessageDialog(null, "No se ha encontrado ningun sorteo en esta fecha", "INFO", JOptionPane.INFORMATION_MESSAGE);
					   }else{
						   corporativo.mostarReporteTurno(turnov);  
					   }
					   
				   }
				
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			
		    }
		    
		   }
    	
    	);
    	
    	buttonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
    			
            @Override
            public void mousePressed(MouseEvent e) {
            	//p = new PantallaCargando();
            	//p.setProgreso("Cargando...", 50);
            }
        });
    	
    	//Tabla
    	DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Estado");
        tableTurnos.setModel(modelo);
        scrollListaTurnos.setViewportView(tableTurnos);
    	
    	fondo.setBounds(0,0, 400,250);
    	fondo.setLayout(new FlowLayout());

    	fondo.add(labelDescription);
    	fondo.add(labelFechaInicial);
    	fondo.add(chooserFechaInicial);
    	fondo.add(labelFechaFinal);
    	fondo.add(chooserFechaFinal);
    	fondo.add(buttonBuscar);
    	fondo.add(scrollListaTurnos);
    	tableTurnos.setSize(50, 50);
    	scrollListaTurnos.setSize(50, 50);
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
		    }else if(Util.diferenciaFechas(chooserFechaInicial.getDate(),chooserFechaFinal.getDate())!=7){
		    	JOptionPane.showMessageDialog(null, "Se puede generar sorteo solo por semana", "error", JOptionPane.ERROR_MESSAGE);
		    }		
			turnov.setFechaInicio(chooserFechaInicial.getDate());
	    	turnov.setFechaFin(chooserFechaFinal.getDate());    	
	    	turnov.setFechaCreacion(new Date(corporativo.getFecha()));
			return true;
		}
	
}
