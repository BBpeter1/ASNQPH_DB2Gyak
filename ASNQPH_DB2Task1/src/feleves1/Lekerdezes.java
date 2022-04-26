package feleves1;

public class Lekerdezes {
	
	private String nev;
	private String cim;
	
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
	public Lekerdezes(String nev, String cim) {
		super();
		this.nev = nev;
		this.cim = cim;
	}
	@Override
	public String toString() {
		return nev + " " + cim;
	}

}
