package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import dtos.PersonaDTO;

public class CorporativoDAOImpl implements CorporativoDAO {
	private Connection userConn;
	
	
	
	public PersonaDTO obtenerPersonaPorId(Long id) throws SQLException{
		Connection conn=null;
		Statement stmt=null;
		ResultSet datos=null;
		PersonaDTO persona=new PersonaDTO();
		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			stmt=conn.createStatement();
            datos=stmt.executeQuery("SELECT * FROM persona WHERE ID=1");
            while(datos.next()){
            	persona.setId(datos.getLong(1));
            	persona.setNombre(datos.getString(2));
            	persona.setEstado(datos.getString(3));
            }
		}
		finally{
			Conexion.close(stmt);
			if(this.userConn==null){
				Conexion.close(conn);
			}
		} 
        return persona;
	}
	public void generarMatrizTurno(){
		
	}

}




