package dao;

import java.sql.SQLException;

import dtos.PersonaDTO;
import dtos.TurnoDTO;

public interface CorporativoDAO {

	public PersonaDTO obtenerPersonaPorId(Long id) throws SQLException;

	public void generarMatrizTurno();

	public String getFecha();

	public void guardarTurno(TurnoDTO turno) throws SQLException;
	
}
