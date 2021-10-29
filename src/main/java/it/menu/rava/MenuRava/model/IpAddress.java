package it.menu.rava.MenuRava.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class IpAddress {

	public IpAddress() {
		this.panini = new ArrayList<Panino>();
	}
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String ipaddress;
	
	@ManyToMany(mappedBy = "indirizzi")
	private List<Panino> panini;
	
	
	public void addPanino(Panino p) {
		this.panini.add(p);
	}
	
	public void removePanino(Panino p) {
		this.panini.remove(p);
	}
}
