package com.example.lojamarcao.service;

import com.example.lojamarcao.model.Categoria;
import com.example.lojamarcao.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria atualizar(Long cod, Categoria categoria){
        Categoria categoriaSalva = this.categoriaRepository.findById(cod)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        if (categoriaSalva == null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(categoria, categoriaSalva, "cod");
        return this.categoriaRepository.save(categoriaSalva);
    }
}
