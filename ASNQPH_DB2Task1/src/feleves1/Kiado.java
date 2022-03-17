package feleves1;

import java.util.Date;

public class Kiado {
	
	private int kiaodid;
	private String nev;
	private Date kiadaseve;
	//private List<Konyv> konyv
	
	
	public Kiado(int kiaodid, String nev, Date kiadaseve) {
		super();
		this.kiaodid = kiaodid;
		this.nev = nev;
		this.kiadaseve = kiadaseve;
	}
	public int getKiaodid() {
		return kiaodid;
	}
	public void setKiaodid(int kiaodid) {
		this.kiaodid = kiaodid;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	public Date getKiadaseve() {
		return kiadaseve;
	}
	public void setKiadaseve(Date kiadaseve) {
		this.kiadaseve = kiadaseve;
	}
	@Override
	public String toString() {
		return kiaodid + " " + nev + " " + kiadaseve;
	}
	
	

}
