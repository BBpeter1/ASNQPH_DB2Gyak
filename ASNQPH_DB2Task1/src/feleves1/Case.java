package feleves1;

public class Case {
	
	private String nev;
	private String cim;
	private String kategoria;
	public Case(String nev, String cim, String kategoria) {
		super();
		this.nev = nev;
		this.cim = cim;
		this.kategoria = kategoria;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
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
	@Override
	public String toString() {
		return nev + " " + cim + " " + kategoria + "\n";
	}
	
}
