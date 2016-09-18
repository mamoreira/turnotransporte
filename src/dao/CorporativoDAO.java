package dao;

import java.sql.SQLException;

import dtos.PersonaDTO;

public interface CorporativoDAO {

	public PersonaDTO obtenerPersonaPorId(Long id) throws SQLException;
}
