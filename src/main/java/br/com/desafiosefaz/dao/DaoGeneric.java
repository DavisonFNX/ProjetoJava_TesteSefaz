package br.com.desafiosefaz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import br.com.desafiosefaz.jpautil.JPAUtil;

public class DaoGeneric<T> {
	
	final Class<T> typeParameterClass;
	
	

	public DaoGeneric() {
		super();
		typeParameterClass = (Class<T>) getClass().getGenericSuperclass();
	}

	public void save(T entidade) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(entityManager.contains(entidade) ? entidade : entityManager.merge(entidade));
		entityTransaction.commit();
		entityManager.close();

	}
	
	public T merge(T entidade) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		
		entityTransaction.begin();
		T retorno = entityManager.merge(entidade);
		entityManager.merge(entidade);
		entityTransaction.commit();
		entityManager.close();
		
		return retorno;

	}
	
	public T find( Integer id,T entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<T> retorno = entityManager.createQuery("From "+ entidade.getClass().getCanonicalName() + " where id = :id", typeParameterClass).setParameter("id", id).getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		return retorno.get(0);
		
	}

	public T find(String nome, String senha,T entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		List<T> retorno = entityManager.createQuery("From "+ entidade.getClass().getCanonicalName() + " where nome = :nome and senha = :senha", typeParameterClass).setParameter("nome", nome).setParameter("senha", senha).getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return retorno.get(0);

	}

	public void delete(T entidade) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.remove(entityManager.contains(entidade) ? entidade : entityManager.merge(entidade));
		entityTransaction.commit();
		entityManager.close();

	}

	public void deletePorId(T  entidade) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		
//		Object id = JPAUtil.getPrimeryKey(entidade);
		//entityManager.createQuery("delete from " + getTableName(entityManager, entidade) + " where id = " + id, typeParameterClass).executeUpdate();
		//entityManager.createQuery("delete from " + entidade.getClass().getClass().getCanonicalName() + " where id = " + id).executeUpdate();
		//entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id).executeUpdate();

		entityManager.remove(entityManager.contains(entidade) ? entidade : entityManager.merge(entidade));
				
		entityTransaction.commit();
		entityManager.close();

	}


	public List<T> getListEntity(Class<T> entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		List<T> retorno = entityManager.createQuery("from " + getTableName(entityManager, entidade), typeParameterClass).getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return retorno;

	}
	
	private static <T> String getTableName(EntityManager em, Class<T> entityClass) {

		Metamodel meta = em.getMetamodel();
	    EntityType<T> entityType = meta.entity(entityClass);

	    return entityType.getName();
	}
		

}
