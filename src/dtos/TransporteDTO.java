package dtos;

public class TransporteDTO {
	private Long id;
	private PersonaDTO persona;
	private Long disco;
	private String estado;//A: Activo I: Inactivo
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PersonaDTO getPersona() {
		return persona;
	}
	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}
	public Long getDisco() {
		return disco;
	}
	public void setDisco(Long disco) {
		this.disco = disco;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
