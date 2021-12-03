package br.com.alura.auction.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.alura.auction.model.Lance;
import br.com.alura.auction.model.Leilao;

@Repository
public class LanceDao {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Lance lance) {
		em.persist(lance);
	}

	public Lance buscarMaiorLanceDoLeilao(Leilao leilao) {
		return em.createQuery("SELECT l FROM Lance l WHERE l.valor = (SELECT MAX(lance.valor) FROM Lance lance)", Lance.class)
				.setParameter("leilao", leilao)
				.getSingleResult();
	}
	
}
