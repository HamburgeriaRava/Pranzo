package it.menu.rava.MenuRava.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.menu.rava.MenuRava.model.IpAddress;
import it.menu.rava.MenuRava.repository.IpAddressRepository;

@Service
public class IpAddressService {

	@Autowired
	private IpAddressRepository iprepo;
	

	@Transactional
	public boolean exists(Long id_ip, Long id_panino) {
		return this.iprepo.checkContains(id_ip, id_panino) != null;
	}
	
	@Transactional
	public IpAddress findByAddress(String add) {
		return this.iprepo.findByIpaddress(add);
	}
	
	@Transactional
	public void save(IpAddress ipadd) {
		this.iprepo.save(ipadd);
	}
	
	@Transactional
	public void deleteRow(Long id1, Long id2) {
		this.iprepo.deleteRow(id1, id2);
	}
}
