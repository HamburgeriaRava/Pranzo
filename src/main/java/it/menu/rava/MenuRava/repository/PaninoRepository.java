package it.menu.rava.MenuRava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.menu.rava.MenuRava.model.Panino;

public interface PaninoRepository extends CrudRepository<Panino, Long> {

	@Query(value = "SELECT t.panini_id FROM panino_indirizzi t WHERE t.indirizzi_id = ?1", nativeQuery = true)
	public List<Long> checkContainsPanino(Long id1);
	
	public List<Panino> findAllByOrderByIdAsc();
}
