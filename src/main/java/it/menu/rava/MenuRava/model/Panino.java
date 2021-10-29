package it.menu.rava.MenuRava.model;


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
public class Panino {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String nome;
	@Column
	private Float prezzo;
	@Column(length = 5000)
	private String descrizione;
	@ManyToMany
	private List<IpAddress> indirizzi;
	@Column
	private Long numeroConsigliati;
	
	public void add(IpAddress ipaddress) {
		this.indirizzi.add(ipaddress);
	}
	
	public void remove(IpAddress ip) {
		this.indirizzi.remove(ip);
	}
}
