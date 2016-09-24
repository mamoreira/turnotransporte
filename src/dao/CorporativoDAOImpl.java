package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import conexion.Conexion;
import dtos.PersonaDTO;
import dtos.PuestoDTO;
import dtos.TransporteDTO;
import dtos.TurnoDTO;
import dtos.TurnoDetalleDTO;

public class CorporativoDAOImpl implements CorporativoDAO {
	private Connection userConn;
	

	private final String SQL_INSERT_TURNODETALLE="INSERT INTO turnodetalle(turno_id,puesto_id,transporte_id) VALUES(?,?,?)";

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
	public void generarMatrizTurno(TurnoDTO turno) throws SQLException{
		ArrayList<String> dias=new ArrayList<>();
		dias.add("lunes");
		dias.add("martes");
		dias.add("miercoles");
		dias.add("jueves");
		dias.add("viernes");
		dias.add("sabado");
		int fichaDisco=0;
		List<Long> discos=obtenerDiscosTransporte(); //obtiene numero de discos para sorteo
		List<TurnoDetalleDTO> turnoDetalleList=new ArrayList<TurnoDetalleDTO>();
		for (String dia : dias) {
			List<PuestoDTO> puestos=obtenerPrimerosPuestos(dia);
			for (PuestoDTO puestoDTO : puestos) {
				fichaDisco =(int) (Math.random()*discos.size()+0);
				TurnoDetalleDTO turnoDetalle= new TurnoDetalleDTO();
				turnoDetalle.setTurno(turno);
				turnoDetalle.setPuesto(puestoDTO);
				turnoDetalle.setTransporte(obtenerTransportePorDisco(discos.get(fichaDisco)));
				discos.remove(discos.get(fichaDisco));
				guardarTurnoDetalle(turnoDetalle);
				turnoDetalleList.add(turnoDetalle);
			}
		}
	}
	private int guardarTurnoDetalle(TurnoDetalleDTO turnoDetalle) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		int rows=0;
		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			System.out.println("Ejecutando query: "+SQL_INSERT_TURNODETALLE);
			stmt=conn.prepareStatement(SQL_INSERT_TURNODETALLE);
			int index=1;
			stmt.setLong(index++, turnoDetalle.getTurno().getId());
			stmt.setLong(index++, turnoDetalle.getPuesto().getId());
			stmt.setLong(index++, turnoDetalle.getTransporte().getId());
			rows=stmt.executeUpdate();
		}
		finally{
			Conexion.close(stmt);
			if(this.userConn==null){
				Conexion.close(conn);
			}
		}
		return rows;		
	}
	private List<Long> obtenerDiscosTransporte() throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Long> discos=new ArrayList<>();

		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			stmt=conn.prepareStatement(
				"select disco from transporte");
			rs=stmt.executeQuery();
	        while (rs.next()) {
	        	Long disco= new Long(rs.getLong(1));
	        	discos.add(disco);
	        }
		}
		finally{
			Conexion.close(stmt);
			if(this.userConn==null){
				Conexion.close(conn);
			}
		}        
        return discos;
	}
	private TransporteDTO obtenerTransportePorDisco(Long long1) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
    	TransporteDTO transporte=new TransporteDTO();

		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			stmt=conn.prepareStatement(
				"select id,disco,estado from transporte where disco="+long1);
			rs=stmt.executeQuery();
	        while (rs.next()) {
	        	transporte.setId(rs.getLong(1));
	        	transporte.setDisco(rs.getLong(2));
	        	transporte.setEstado(rs.getString(3));
	        }
		}
		finally{
			Conexion.close(stmt);
			if(this.userConn==null){
				Conexion.close(conn);
			}
		}        
        return transporte;
	}
	private List<PuestoDTO> obtenerPrimerosPuestos(String dia) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String SQL;
		List<PuestoDTO> puestos=new ArrayList<>();
		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			if(dia.equals("sabado")){
				SQL="select id,hora,dia,turno from puesto where dia ='"+dia
				+ "' and turno<6 order by turno";
			}else{
				SQL="select id,hora,dia,turno from puesto where dia ='"+dia
						+ "' and turno<5 order by turno";
			}
			stmt=conn.prepareStatement(SQL);
			rs=stmt.executeQuery();
	        while (rs.next()) {
	        	PuestoDTO puesto=new PuestoDTO();
	        	puesto.setId(rs.getLong(1));
	        	puesto.setHora(rs.getTime(2));
	        	puesto.setDia(rs.getString(3));
	        	puesto.setTurno(rs.getLong(4));
	            puestos.add(puesto);
	        }
		}
		finally{
			Conexion.close(stmt);
			if(this.userConn==null){
				Conexion.close(conn);
			}
		}        
        return puestos;
	}
	
	public String getFecha(){
		Calendar fecha = Calendar.getInstance();
		 int dia;
		 int mes;
		 int anio;
		 String textFecha;
		 dia=fecha.get(Calendar.DATE);
		 mes=fecha.get(Calendar.MONTH)+1;
		 anio=fecha.get(Calendar.YEAR);
		 textFecha=new String(String.format("%02d", dia)+"/"+String.format("%02d", mes)+"/"+Integer.toString(anio));
		return textFecha;
	}
	
	
	
	public void guardarTurno(TurnoDTO turno) throws SQLException{
		Connection conn=null;
		Statement stmt=null;
		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			stmt=conn.createStatement();
            stmt.execute("INSERT INTO TURNO()");
		}
		finally{
			Conexion.close(stmt);
			if(this.userConn==null){
				Conexion.close(conn);
			}
		} 
	}

}




