package com.teste.cadastrodepessoas.repositories;

import com.teste.cadastrodepessoas.entities.Endereco;
import com.teste.cadastrodepessoas.entities.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Enderecos {

    private final EntityManager entityManager;

    public Enderecos(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Endereco> findAll() {
        TypedQuery<Endereco> query = this.entityManager.createQuery("FROM Endereco", Endereco.class);
        return query.getResultList();
    }

    public Endereco findByIdPessoa(Long id) {
        Pessoa pessoa = this.entityManager.find(Pessoa.class, id);

        String jpql = "FROM Endereco WHERE pessoa = :pessoa";
        TypedQuery<Endereco> query = this.entityManager.createQuery(jpql, Endereco.class);

        query.setParameter("pessoa", pessoa);

        return query.getSingleResult();
    }

    public Long save(Endereco endereco) {
        return this.entityManager.merge(endereco).getId();
    }

    public void delete(Long id) {
        Endereco endereco = this.entityManager.find(Endereco.class, id);
        this.entityManager.remove(endereco);
    }

}
