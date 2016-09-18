package run;

import java.sql.SQLException;

import dao.CorporativoDAO;
import dao.CorporativoDAOImpl;
import dtos.PersonaDTO;

public class Run {

	public static void main(String[] args) {
		CorporativoDAO corporativo= new CorporativoDAOImpl();
		try {
			PersonaDTO p=corporativo.obtenerPersonaPorId(1L);
			System.out.println(p.getId()+ " "+ p.getNombre());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
