package util;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.ResourceBundle;

public class Util {

	
	public static String FONT;
	public static String FONT_LABEL_SIZE;
	public static String FONT_SIZE_BUTTON;
	private static String JDBC_FILE_NAME="Propiedades";
	
	public static Properties loadProperties(){
		Properties prop=new Properties();
		ResourceBundle bundle=ResourceBundle.getBundle(JDBC_FILE_NAME);
		Enumeration<?> e=bundle.getKeys();
		String key=null;
		while(e.hasMoreElements()){
			key=(String) e.nextElement();
			prop.put(key,bundle.getObject(key));
		}
		FONT=prop.getProperty("font");
		FONT_LABEL_SIZE=prop.getProperty("sizeLabel");
		FONT_SIZE_BUTTON=prop.getProperty("sizebutton");
		return prop;
	}
	
	
	public static int getDiaSemana(Date fecha) {
        GregorianCalendar fechaCalendario = new GregorianCalendar();
        fechaCalendario.setTime(fecha);
        return fechaCalendario.get(Calendar.DAY_OF_WEEK);
	}
    public static int diferenciaFechas(Date cfechaIni,Date cfechaFin){
    	Calendar c = Calendar.getInstance();
    	 
    	//fecha inicio
    	 
    	Calendar fechaInicio = new GregorianCalendar();
    	 
    	fechaInicio.setTime(cfechaIni);
    	//fecha fin
    	 
    	Calendar fechaFin = new GregorianCalendar();
    	 
    	fechaFin.setTime(cfechaFin);
    	 
    	//restamos las fechas como se puede ver son de tipo Calendar,
    	 
    	//debemos obtener el valor long con getTime.getTime.
    	 
    	c.setTimeInMillis(
    	 
    	       fechaFin.getTime().getTime() - fechaInicio.getTime().getTime());
    	 
    	//la resta provoca que guardamos este valor en c,
    	 
    	//los milisegundos corresponde al tiempo en dias
    	 
    	//asi sabemos cuantos dias
 
    	return c.get(Calendar.DAY_OF_YEAR);
    }
}
