package frames;
import java.sql.SQLException;
import java.util.Map;

import conexion.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
public abstract class AbstractJasperReport {

	
	private static JasperReport report;
	private static JasperPrint reportFilled;
	private static JasperViewer viewer;

	public static void createReport(String path, Map<String,Object > parametros){
		try{
			report=(JasperReport) JRLoader.loadObjectFromFile(path);
			reportFilled=JasperFillManager.fillReport(report,parametros, Conexion.getConnection());
		}
		catch(JRException | SQLException e){
			e.printStackTrace();
		}		
	}
	
	public static void showViewer(){
		viewer= new JasperViewer(reportFilled,false);
		viewer.setVisible(true);
	}
	public static void exportToPdf(String destino){
		
	}

}
