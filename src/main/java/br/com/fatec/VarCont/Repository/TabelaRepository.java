package br.com.fatec.VarCont.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fatec.VarCont.DataSource.Models.Tabela;

public interface TabelaRepository extends JpaRepository<Tabela, Long> {
	@Query(value = "SELECT * FROM tbl_tabela WHERE prod_id = :prod_id ORDER BY tabela_data", nativeQuery = true)
	List<Tabela> findByIdProd(@Param("prod_id") Long idProduto);

	@Query(value = "SELECT * FROM tbl_tabela WHERE prod_id = :prod_id AND tabela_data = :data", nativeQuery = true)
	Optional<Tabela> findByData(@Param("prod_id") Long idProduto, @Param("data") Date data);
}
