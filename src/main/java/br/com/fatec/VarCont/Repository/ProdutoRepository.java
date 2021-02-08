package br.com.fatec.VarCont.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatec.VarCont.DataSource.Models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
