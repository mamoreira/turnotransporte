package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import dtos.PersonaDTO;
import dtos.TurnoDTO;

public interface CorporativoDAO {

	public PersonaDTO obtenerPersonaPorId(Long id) throws SQLException;

	public void generarMatrizTurno(TurnoDTO turno) throws SQLException;

	public String getFecha();
	
	public int generarFicha(Random tapilla,int i, ArrayList<Integer> lista);
	
	public void excluirFichas(ArrayList<Integer> lista,ArrayList<Integer> listaexcluidos);
	
	public void excluirFichas(ArrayList<Integer> lista,ArrayList<Integer> listaexcluidos, int limite);

	public void guardarTurno(TurnoDTO turno) throws SQLException;
	
	
}
