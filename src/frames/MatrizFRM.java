package frames;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MatrizFRM extends AbstractFRM{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6312166505526175828L;
     public MatrizFRM (){
		initComponent();
	}

	private void initComponent() {
		setLayout(new BorderLayout());
        setTitle(".::NUEVO TURNO");
    	setSize(300,380);
    	
	}
}
