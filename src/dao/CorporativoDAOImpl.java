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
		int par=5;
		//obtiene numero de discos para sorteo primeros y ultimos puestos
		List<Long> discos=obtenerDiscosTransporte(); 
		//obtiene numero de discos para sorteo puesto de la mitad
		List<Long> discos2=obtenerDiscosTransporte();
		List<Long> discosSorteados;
		
		List<TurnoDetalleDTO> turnoDetalleList=new ArrayList<TurnoDetalleDTO>();
		for (String dia : dias) {
			//obtiene primeros puestos para el sorteor
			List<PuestoDTO> puestos=obtenerPrimerosPuestos(dia);
			//obtiene puestos finales para el sorteo
			List<PuestoDTO> puestosFinales=obtenerUltimosPuestos(dias.get(par));
			//obtiene puestos mitad Inicial
			discosSorteados=new ArrayList<Long>();

			for (PuestoDTO puestoDTO : puestos) {
				fichaDisco =(int) (Math.random()*discos.size()+0);
				TurnoDetalleDTO turnoDetalle= new TurnoDetalleDTO();
				turnoDetalle.setTurno(turno);
				turnoDetalle.setPuesto(puestoDTO);
				turnoDetalle.setTransporte(obtenerTransportePorDisco(discos.get(fichaDisco)));
				discosSorteados.add(discos.get(fichaDisco));
				discos.remove(discos.get(fichaDisco));
				guardarTurnoDetalle(turnoDetalle);
				turnoDetalleList.add(turnoDetalle);
			}
			for (PuestoDTO puestoDTO : puestosFinales) {
				fichaDisco =(int) (Math.random()*discosSorteados.size()+0);
				TurnoDetalleDTO turnoDetalle= new TurnoDetalleDTO();
				turnoDetalle.setTurno(turno);
				turnoDetalle.setPuesto(puestoDTO);
				turnoDetalle.setTransporte(obtenerTransportePorDisco(discosSorteados.get(fichaDisco)));
				discosSorteados.remove(discosSorteados.get(fichaDisco));
				guardarTurnoDetalle(turnoDetalle);
				turnoDetalleList.add(turnoDetalle);
			}
			par--;
			}
		
		par=5;
		for (String dia : dias){
			List<PuestoDTO> puestosMitadInicial=obtenerMitadInicialPuestos(dia);
			//obtiene puestos mitad Final
			List<PuestoDTO> puestosMitadFinal=obtenerMitadFinalPuestos(dias.get(par));
			//OBTENER DISCOS FALTANTES DEL SORTEO POR DIA
			List<Long> discosFaltaSorteo=obtenerDiscosFaltaSorteoPorDia(dia,turno);
			discosSorteados=new ArrayList<>();
			for (PuestoDTO puestoDTO : puestosMitadInicial) {
				fichaDisco =(int) (Math.random()*discosFaltaSorteo.size()+0);
				TurnoDetalleDTO turnoDetalle= new TurnoDetalleDTO();
				turnoDetalle.setTurno(turno);
				turnoDetalle.setPuesto(puestoDTO);
				turnoDetalle.setTransporte(obtenerTransportePorDisco(discosFaltaSorteo.get(fichaDisco)));
				discosSorteados.add(discosFaltaSorteo.get(fichaDisco));
				discosFaltaSorteo.remove(discosFaltaSorteo.get(fichaDisco));
				guardarTurnoDetalle(turnoDetalle);
				turnoDetalleList.add(turnoDetalle);
			}
			discosSorteados.sort(null);
			System.out.println(discosSorteados);
			
			for (PuestoDTO puestoDTO : puestosMitadFinal) {
				fichaDisco =(int) (Math.random()*discosSorteados.size()+0);
				TurnoDetalleDTO turnoDetalle= new TurnoDetalleDTO();
				turnoDetalle.setTurno(turno);
				turnoDetalle.setPuesto(puestoDTO);
				turnoDetalle.setTransporte(obtenerTransportePorDisco(discosSorteados.get(fichaDisco)));
				discosSorteados.remove(discosSorteados.get(fichaDisco));
				guardarTurnoDetalle(turnoDetalle);
				turnoDetalleList.add(turnoDetalle);
			}
			par--;
		}
		List<Long> discosDomingo= new ArrayList<>();
		if(turno.getId()==1){
			discos2.sort(null);
			discosDomingo=discos2.subList(0,14);
		}
		else{
			Long reporteId=turno.getId()-1;
			int ref=discos2.indexOf(obtenerDiscoReferenciaDomingo(reporteId));
			int puntero=0;
			int indice=0;
			while(puntero<15){
				while(ref<25){
					discosDomingo.add(discos2.get(ref));
					ref++;
					puntero++;
				}
				discosDomingo.add(discos2.get(indice));
				puntero++;
			}
		}
		List<PuestoDTO> puestosDomingo=obtenerPuestosDomingo();
		for (PuestoDTO puestoDTO : puestosDomingo) {
			fichaDisco =(int) (Math.random()*discosDomingo.size()+0);
			TurnoDetalleDTO turnoDetalle= new TurnoDetalleDTO();
			turnoDetalle.setTurno(turno);
			turnoDetalle.setPuesto(puestoDTO);
			turnoDetalle.setTransporte(obtenerTransportePorDisco(discosDomingo.get(fichaDisco)));
			guardarTurnoDetalle(turnoDetalle);
			turnoDetalleList.add(turnoDetalle);
		}
		
	}

	private List<PuestoDTO> obtenerMitadFinalPuestos(String dia) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String SQL;
		List<PuestoDTO> puestos=new ArrayList<>();
		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			if((dia.equals("jueves"))||(dia.equals("viernes"))||(dia.equals("sabado"))){
				SQL="select id,hora,dia,turno from puesto where dia ='"+dia
				+ "' and turno>13 and turno <22 order by turno";
			}else{
				if((dia.equals("martes"))||(dia.equals("miercoles"))){
					SQL="select id,hora,dia,turno from puesto where dia ='"+dia
							+ "' and turno>12 and turno <22 order by turno";
				}else{
					SQL="select id,hora,dia,turno from puesto where dia ='"+dia
							+ "' and turno>12  and turno<21 order by turno";
				}
				
			}
			
			stmt=conn.prepareStatement(SQL);
			rs=stmt.executeQuery();
	        while (rs.next()) {
	        	PuestoDTO puesto=new PuestoDTO();
	        	puesto.setId(rs.getLong(1));
	        	puesto.setHora(rs.getString(2));
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
	private List<PuestoDTO> obtenerMitadInicialPuestos(String dia) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String SQL;
		List<PuestoDTO> puestos=new ArrayList<>();
		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			if(dia.equals("sabado")){
				SQL="select id,hora,dia,turno from puesto where dia ='"+dia
				+ "' and turno>5 and turno <14 order by turno";
			}else{
				if((dia.equals("jueves"))||(dia.equals("viernes"))){
					SQL="select id,hora,dia,turno from puesto where dia ='"+dia
					+ "' and turno>4 and turno <14 order by turno";
				}else{
				SQL="select id,hora,dia,turno from puesto where dia ='"+dia
						+ "' and turno>4  and turno<13 order by turno";
				}
				}
			stmt=conn.prepareStatement(SQL);
			rs=stmt.executeQuery();
	        while (rs.next()) {
	        	PuestoDTO puesto=new PuestoDTO();
	        	puesto.setId(rs.getLong(1));
	        	puesto.setHora(rs.getString(2));
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
	private List<Long> obtenerDiscosFaltaSorteoPorDia(String dia, TurnoDTO turno) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Long> discos=new ArrayList<>();

		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			stmt=conn.prepareStatement(
				"select disco from transporte t where t.id not in ("
				+ " select transporte_id from turnodetalle td"
				+ " join puesto p on p.id=td.puesto_id"
				+ " where dia='"+dia+ "'"
				+ "      and td.turno_id="+turno.getId()+")"
				+ "order by disco");
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
	
	private List<PuestoDTO> obtenerPuestosDomingo() throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String SQL;
		List<PuestoDTO> puestos=new ArrayList<>();
		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			SQL="select id,hora,dia,turno from puesto where dia ='domingo'"
					+ " order by turno";
			stmt=conn.prepareStatement(SQL);
			rs=stmt.executeQuery();
	        while (rs.next()) {
	        	PuestoDTO puesto=new PuestoDTO();
	        	puesto.setId(rs.getLong(1));
	        	puesto.setHora(rs.getString(2));
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
	
	private int guardarTurnoDetalle(TurnoDetalleDTO turnoDetalle) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		int rows=0;
		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			//System.out.println("Ejecutando query: "+SQL_INSERT_TURNODETALLE);
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
	
	private List<Long> obtener15DiscosTransporte() throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Long> discos=new ArrayList<>();

		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			stmt=conn.prepareStatement(
				"select disco from transporte where id<16");
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
	        	puesto.setHora(rs.getString(2));
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
	
	private List<PuestoDTO> obtenerUltimosPuestos(String dia) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String SQL;
		List<PuestoDTO> puestos=new ArrayList<>();
		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			if(dia.equals("lunes")){
				SQL="select id,hora,dia,turno from puesto where dia ='"+dia
				+ "' and turno>20 order by turno";
			}else{
				SQL="select id,hora,dia,turno from puesto where dia ='"+dia
						+ "' and turno>21 order by turno";
			}
			stmt=conn.prepareStatement(SQL);
			rs=stmt.executeQuery();
	        while (rs.next()) {
	        	PuestoDTO puesto=new PuestoDTO();
	        	puesto.setId(rs.getLong(1));
	        	puesto.setHora(rs.getString(2));
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
	
	private Long obtenerDiscoReferenciaDomingo(Long turno) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Long disco;

		try{
			conn=(this.userConn!=null)?this.userConn:Conexion.getConnection();
			stmt=conn.prepareStatement(
				"select disco from transporte t where t.id = ("
				+ " select max(transporte_id) from turnodetalle td"
				+ " join puesto p on p.id=td.puesto_id"
				+ " where dia='domingo'"
				+ " and td.turno_id="+turno+")");
			rs=stmt.executeQuery();
	        disco= new Long(rs.getLong(1));
		}
		finally{
			Conexion.close(stmt);
			if(this.userConn==null){
				Conexion.close(conn);
			}
		}        
        return disco;
	}
	
	private List<Long> obtener15Discos(int referencia,List<Long>discospasados) {
		List <Long> discos= new ArrayList<>();
		int puntero=0;
		int ref=0;
		int i=0;
		while(i<15){
			while(ref<25){
				discos.add(discospasados.get(referencia));
				ref++;
				i++;
			}
			discos.add(discospasados.get(puntero));
			i++;
			puntero++;
		}
		return discos;
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
	
	public int generarFicha(Random tapilla,int i, ArrayList<Integer> lista){
		int fichasDisco;
		do{
			 fichasDisco =tapilla.nextInt(i)+1;//sorteo
		 }while(lista.contains(fichasDisco));
		return fichasDisco;
	}
	
	public void excluirFichas(ArrayList<Integer> lista,ArrayList<Integer> listaexcluidos){
		for(Integer disco: lista){
			 listaexcluidos.add(disco);
		 }
	}
	
	public void excluirFichas(ArrayList<Integer> lista,ArrayList<Integer> listaExcluidos,int limite){
		int maximo=0;
		for (Integer disco: lista){
			listaExcluidos.add(disco);
			maximo++;
			if (maximo== limite)
				return;
		}
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




