package com.exemplo.controleprodutosms.repository;

import java.util.Date;
import java.util.List;

import com.exemplo.controleprodutosms.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface produtoRepositorio extends JpaRepository<Produto, Integer>{

    List<Produto> findByDataCadastro(Date dataCadastro);
    
}
