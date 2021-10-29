package it.menu.rava.MenuRava.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.menu.rava.MenuRava.model.IpAddress;
import it.menu.rava.MenuRava.model.Panino;
import it.menu.rava.MenuRava.repository.PaninoRepository;

@Service
public class PaninoService {

	@Autowired
	private PaninoRepository paninoRepo;
	
	@Transactional
	public long count() {
		return this.paninoRepo.count();
	}
	
	@Transactional
	public List<Panino> all(){
		return (List<Panino>) this.paninoRepo.findAllByOrderByIdAsc();
	}
	
	@Transactional
	public Panino findById(Long id) {
		Optional<Panino> panino = this.paninoRepo.findById(id);
		return panino.orElse(null);
	}
	
	@Transactional
	public void save(Panino panino) {
		this.paninoRepo.save(panino);
	}
	
	@Transactional
	public List<Long> containsPanino(IpAddress ipaddr){
		return this.paninoRepo.checkContainsPanino(ipaddr.getId());
	}
}
