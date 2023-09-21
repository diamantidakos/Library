package com.mgiandia.library;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;

/**
 * Βασική κλάση για integration test 
 * Αρχικοποιεί τη βάση δεδομένων πριν από κάθε έλεγχο
 */
public class IntegrationBase {
	@Inject
	EntityManager em;
	
	@Transactional
	@BeforeEach
	public void initDb()  {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("import.sql");
		String sql = convertStreamToString(in);
		try {
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		em.createNativeQuery(sql).executeUpdate();
	}
	
	
	private String convertStreamToString(InputStream in) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(in,"UTF-8").useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}
