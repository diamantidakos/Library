package com.mgiandia.library.resource;

import javax.persistence.EntityManager;

import com.mgiandia.library.persistence.JPAUtil;

public class AbstractResource {

	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();

	}

}
