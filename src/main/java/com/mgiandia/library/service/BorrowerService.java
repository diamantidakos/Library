package com.mgiandia.library.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.persistence.JPAUtil;

public class BorrowerService {

	private EntityManager em;

	public BorrowerService() {
		em = JPAUtil.getCurrentEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<Borrower> findBorrowersByLastName(String last_name) {

		List<Borrower> results = null;
		results = em
				.createQuery(
						"select b from Borrower b where b.person.lastName like :surname ")
				.setParameter("surname", last_name).getResultList();

		return results;
	}
/*
	public Borrower createBorrower(Map<String, String> data) {
		Borrower b = new Borrower();

		try {
			b.setBorrowerNo(Integer.valueOf(data
					.get(BorrowerInfo.BORROWERNO_KEY)));
			b.setFirstName(data.get(BorrowerInfo.FIRSTNAME_KEY));
			b.setLastName(data.get(BorrowerInfo.LASTNAME_KEY));
			b.setEmail(data.get(BorrowerInfo.EMAIL_KEY));
			b.setTelephone(data.get(BorrowerInfo.TELEPHONE_KEY));

			return b;
		} catch (Exception e) {
			return null;
		}

	}
*/
	public Borrower findBorrowerById(int id) {
		return em.find(Borrower.class, id);
	}

	public boolean saveOrUpdateBorrower(Borrower b) {

		if (b != null) {
			em.merge(b);
			return true;
		}

		return false;
	}

	/**
	 * Creates a new borrower instance in the database
	 * @param b
	 * @return
	 */
	public boolean createBorrower(Borrower b) {

		if (b != null) {
			em.persist(b);
			return true;
		}

		return false;
	}
	
	public boolean deleteBorrower(Borrower b) {

		if (b != null) {
			em.remove(b);
			return true;
		}

		return false;
	}

	public List<Borrower> findAllBorrowers() {
		List<Borrower> results = null;

		results = em.createQuery("select b from Borrower b", Borrower.class)
				.getResultList();

		return results;
	}
}
