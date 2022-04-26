package feleves1;

public class Konyv {

	private int kid;
	private int ar;
	private String cim;
	private String kategoria;
	private String szerzo;
	private int kiadoid;
	public Konyv(int kid, int ar, String cim, String kategoria, String szerzo, int kiadoid) {
		super();
		this.kid = kid;
		this.ar = ar;
		this.cim = cim;
		this.kategoria = kategoria;
		this.szerzo = szerzo;
		this.kiadoid = kiadoid;
	}
	public int getKid() {
		return kid;
	}
	public void setKid(int kid) {
		this.kid = kid;
	}
	public int getAr() {
		return ar;
	}
	public void setAr(int ar) {
		this.ar = ar;
	}
	public String getCim() {
		return cim;
	}
	public void setCim(String cim) {
		this.cim = cim;
	}
	public String getKategoria() {
		return kategoria;
	}
	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}
	public String getSzerzo() {
		return szerzo;
	}
	public void setSzerzo(String szerzo) {
		this.szerzo = szerzo;
	}
	public int getKiadoid() {
		return kiadoid;
	}
	public void setKiadoid(int kiadoid) {
		this.kiadoid = kiadoid;
	}
	@Override
	public String toString() {
		return kid + " " + ar + " " + cim + " " + kategoria + " " + szerzo
				+ " " + kiadoid;
	}
	
	
	
}
