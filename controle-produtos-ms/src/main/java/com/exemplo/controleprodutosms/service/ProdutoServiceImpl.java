package com.exemplo.controleprodutosms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.exemplo.controleprodutosms.compartilhado.ProdutoDto;
import com.exemplo.controleprodutosms.model.Produto;
import com.exemplo.controleprodutosms.repository.produtoRepositorio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService{
    @Autowired
    private produtoRepositorio repo;

    @Override
    public ProdutoDto criarProduto(ProdutoDto produto) {
        return salvarProduto(produto);
    }

    @Override
    public List<ProdutoDto> obterTodos() {
        List<Produto> produtos = repo.findAll();

        return produtos.stream()
            .map(produto -> new ModelMapper().map(produto, ProdutoDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<ProdutoDto> obterPorId(Integer id) {
        Optional<Produto> produto = repo.findById(id);

        if(produto.isPresent()) {
            return Optional.of(new ModelMapper().map(produto.get(), ProdutoDto.class));
        }
 
        return Optional.empty();
    }

    @Override
    public List<ProdutoDto> obterPorData(Date data) {
        List<Produto> produtos = repo.findByDataCadastro(data);

        return produtos.stream()
            .map(produto -> new ModelMapper().map(produto, ProdutoDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public void removerProduto(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public ProdutoDto atualizarProduto(Integer id, ProdutoDto produto) {
        produto.setId(id);
        return salvarProduto(produto);
    }

    private ProdutoDto salvarProduto(ProdutoDto produto) {
        ModelMapper mapper = new ModelMapper();
        Produto produtoEntidade = mapper.map(produto, Produto.class);
        produtoEntidade.geraValorTotal();
        produtoEntidade = repo.save(produtoEntidade);

        return mapper.map(produtoEntidade, ProdutoDto.class);
    }
}
