package run;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class RunFRM extends AbstractFRM{

	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6704917066251864499L;
	private FondoInicial fondo;
	private javax.swing.JPanel panelBotones;
	private javax.swing.JPanel panelContenedor;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JButton buttonProcesar;

	public RunFRM() throws SQLException{
		initComponents();
	}
	public final void initComponents() throws SQLException{
    	setLayout(new BorderLayout());
        setTitle(".::ASIGNAR TURNO");
        Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize(); 
    	setSize((pantallaTamano.width-100), (pantallaTamano.height-100)); 
        panelBotones = new javax.swing.JPanel();
        panelContenedor=new javax.swing.JPanel();
        scrollPanel=new JScrollPane();
        fondo=new FondoInicial();
    	buttonProcesar = new javax.swing.JButton();
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
		panelBotones.add(buttonProcesar);
    	panelContenedor.setLayout(new BorderLayout()); 
        panelBotones.setPreferredSize(new Dimension(100,60));
        panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Acciones")); 
        panelContenedor.add(panelBotones,BorderLayout.NORTH);
        scrollPanel.setViewportView(panelContenedor);

        add(panelContenedor);
        setLocationRelativeTo(null);
        

    }

    protected void accionProcesar()  throws SQLException{
		// TODO Auto-generated method stub
		
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
