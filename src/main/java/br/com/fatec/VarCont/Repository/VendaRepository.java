package br.com.fatec.VarCont.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fatec.VarCont.DataSource.Models.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {	
	@Query(value = "SELECT * from tbl_venda where prod_id = :prod_id ORDER BY venda_data ASC", nativeQuery=true)
	List<Venda> findListVendas(@Param("prod_id") Long id);
	
	@Query(value = "SELECT * FROM tbl_venda  WHERE lote_id = :id", nativeQuery = true)
	public Optional<Venda> findByLoteId(@Param("id") Long id);
}
