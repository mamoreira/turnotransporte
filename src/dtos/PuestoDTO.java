package dtos;

import java.sql.Time;

public class PuestoDTO {
	private Long id;
	private Time hora;
	private String dia;
	private Long turno;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public Long getTurno() {
		return turno;
	}
	public void setTurno(Long turno) {
		this.turno = turno;
	}
	
}
