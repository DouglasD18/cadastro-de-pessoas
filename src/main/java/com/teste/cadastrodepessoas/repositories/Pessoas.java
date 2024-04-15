package com.teste.cadastrodepessoas.repositories;

import com.teste.cadastrodepessoas.entities.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.*;


public class Pessoas {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmpresaGames");

    EntityManager entityManager = entityManagerFactory.createEntityManager();


    private List<Pessoa> pessoas = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public List<Pessoa> getTodosPessoas() {
        return this.entityManager.createQuery("From Pessoa").getResultList();
    }

    public void setTodosPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    private Pessoa pessoa = new Pessoa();
    private static Pessoa pessoaId = new Pessoa();

    public Pessoa getPessoaId() {
        return this.entityManager.find(Pessoa.class, pessoaId.getId());
    }

    @SuppressWarnings("static-access")
    public void setPessoaId(Pessoa PessoaId) {
        this.pessoaId = PessoaId;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String operacao;
    public List<Pessoa> listaPessoas() {
        return this.pessoas;
    }

    public void consultarPessoasId() {
        entityManager.getTransaction().begin();
        Query p = entityManager.createQuery("From Pessoa");

        @SuppressWarnings("unchecked")
        List<Pessoa> pessoas = p.getResultList();

        for (Pessoa c : pessoas) {
            if(this.pessoa.getId() == c.getId()) {
                pessoaId = entityManager.find(Pessoa.class, c.getId());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Pessoa> consultarTodosPessoas() {
        return this.entityManager.createQuery("From Pessoa").getResultList();
    }

    public void cadastrarPessoas() {
        this.entityManager.getTransaction().begin();

        popularPessoa();

        this.entityManager.persist(this.pessoa);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    public void alteraPessoa () {
        entityManager.getTransaction().begin();
        Query p = entityManager.createQuery("From Pessoa");
        @SuppressWarnings("unchecked")
        List<Pessoa> pessoas = p.getResultList();

        for (Pessoa c : pessoas) {
            if(pessoa.getId() == c.getId()) {
                this.pessoa = entityManager.find(Pessoa.class, pessoa.getId());

                popularPessoa();

                this.entityManager.merge(this.pessoa);
                this.entityManager.getTransaction().commit();
                this.entityManager.close();
                break;
            }
        }
    }

    private void popularPessoa() {
        this.pessoa.setNome(this.pessoa.getNome());
        this.pessoa.setIdade(this.pessoa.getIdade());
        this.pessoa.setSexo(this.pessoa.getSexo());

        this.pessoa.getEndereco().setLogradouro(this.pessoa.getEndereco().getLogradouro());
        this.pessoa.getEndereco().setCidade(this.pessoa.getEndereco().getCidade());
        this.pessoa.getEndereco().setEstado(this.pessoa.getEndereco().getEstado());
        this.pessoa.getEndereco().setNumero(this.pessoa.getEndereco().getNumero());
        this.pessoa.getEndereco().setCep(this.pessoa.getEndereco().getCep());
    }

    public void excluiPessoaUnica() {
        this.entityManager.getTransaction().begin();
        Query p = this.entityManager.createQuery("From Pessoa");
        @SuppressWarnings("unchecked")
        List<Pessoa> pessoas = p.getResultList();

        for (Pessoa c : pessoas) {
            if(c.getId() == c.getId()) {
                this.entityManager.remove(this.pessoa.getId());
                this.entityManager.getTransaction().commit();
                this.entityManager.close();
                break;
            }
        }
    }

    public void excluiTodosPessoas () {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("Delete From Pessoa").executeUpdate();
        this.entityManager.getTransaction().commit();
    }

}
