package run;

import javax.swing.JFrame;

import dao.CorporativoDAO;
import dao.CorporativoDAOImpl;

public class AbstractFRM extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5342780867490148056L;
	protected CorporativoDAO corporativo= new CorporativoDAOImpl();

}
