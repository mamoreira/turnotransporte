package dtos;

public class TurnoDetalleDTO {
  private Long id;
  private TurnoDTO turno;
  private PuestoDTO puesto;
  private TransporteDTO transporte;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TurnoDTO getTurno() {
		return turno;
	}
	public void setTurno(TurnoDTO turno) {
		this.turno = turno;
	}
	public PuestoDTO getPuesto() {
		return puesto;
	}
	public void setPuesto(PuestoDTO puesto) {
		this.puesto = puesto;
	}
	public TransporteDTO getTransporte() {
		return transporte;
	}
	public void setTransporte(TransporteDTO transporte) {
		this.transporte = transporte;
	}
  
}
