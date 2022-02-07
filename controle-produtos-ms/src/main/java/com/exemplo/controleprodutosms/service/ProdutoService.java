package com.exemplo.controleprodutosms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.exemplo.controleprodutosms.compartilhado.ProdutoDto;

public interface ProdutoService {
    ProdutoDto criarProduto(ProdutoDto produto);
    List<ProdutoDto> obterTodos();
    Optional<ProdutoDto> obterPorId(Integer id);
    List<ProdutoDto> obterPorData(Date data);
    void removerProduto(Integer id);
    ProdutoDto atualizarProduto(Integer id, ProdutoDto produto);
}
