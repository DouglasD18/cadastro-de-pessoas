package com.teste.cadastrodepessoas.repositories;

import com.teste.cadastrodepessoas.entities.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class Pessoas {

    private final EntityManager entityManager;

    public Pessoas(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Pessoa findById(Long id) {
        return this.entityManager.find(Pessoa.class, id);
    }

    public List<Pessoa> findAll() {
        TypedQuery<Pessoa> query = this.entityManager.createQuery("FROM Pessoa", Pessoa.class);
        return query.getResultList();
    }

    public Long save(Pessoa pessoa) {
        return this.entityManager.merge(pessoa).getId();
    }

    public void delete(Long id) {
        Pessoa pessoa = this.findById(id);
        this.entityManager.remove(pessoa);
    }

}
