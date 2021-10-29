package it.menu.rava.MenuRava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.menu.rava.MenuRava.model.IpAddress;

public interface IpAddressRepository extends CrudRepository<IpAddress,Long>{

	@Query(value = "SELECT t.indirizzi_id FROM panino_indirizzi t WHERE t.indirizzi_id = ?1 and t.panini_id=?2", nativeQuery = true)
	public Long checkContains(Long id1, Long id2);
	
	@Query(value="DELETE FROM panino_indirizzi t WHERE t.indirizzi_id = ?1 and t.panini_id = ?2", nativeQuery = true)
	public void deleteRow(Long id1, Long id2);
	
	IpAddress findByIpaddress(String ip);

}
