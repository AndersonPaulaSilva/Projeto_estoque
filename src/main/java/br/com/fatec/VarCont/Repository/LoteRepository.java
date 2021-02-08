package br.com.fatec.VarCont.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fatec.VarCont.DataSource.Models.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long>{
	
	@Query(value = "select  tl.prod_id ,tp.prod_nome ,SUM(lote_qtd_total) total, SUM(lote_qtd_compra) qtdComparativa FROM tbl_lote tl JOIN tbl_produto tp ON tl.prod_id = tp.prod_id  group by prod_id", nativeQuery = true)
	 List<Object> findEstoqueTotais();

	
	@Query(value = "SELECT SUM(lote_qtd_total) total FROM tbl_lote WHERE prod_id = :prod_id", nativeQuery = true)
	int findEstoqueTotal(@Param("prod_id") Long idProduto);
	
	
	@Query( value = "SELECT * from tbl_lote WHERE prod_id = :prod_id and lote_data <= now() and lote_qtd_total > 0 ORDER BY lote_data ASC limit 1  ", nativeQuery = true)
	Optional<Lote> findByPepsProximo(@Param("prod_id") Long id);
	
	
	@Query(value = "select * from tbl_lote where prod_id = :prod_id and lote_data <= now() and lote_qtd_total < lote_qtd_compra ORDER BY lote_data DESC limit 1", nativeQuery = true)
	Optional<Lote> findLoteUsado(@Param("prod_id") Long id);
	
	@Query(value= "select * from tbl_lote where prod_id = :prod_id ", nativeQuery = true)
	Optional<Lote> findProdutoLote(@Param("prod_id") Long id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE tbl_lote SET lote_qtd_total = :qtd_total where lote_id = :id", nativeQuery= true)
	void updateQtdTotal(@Param("qtd_total") int qtdTotal, @Param("id") Long id);
}	
