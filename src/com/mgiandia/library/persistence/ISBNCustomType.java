package com.mgiandia.library.persistence;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.hibernate.HibernateException;
import org.hibernate.usertype.*;


import com.mgiandia.library.domain.ISBN;



public class ISBNCustomType implements UserType {
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if ( x == y) return true;
		if ( x== null || y==null) return false;
		return x.equals(y);
	}

	public int hashCode(Object value) throws HibernateException {
		return value.hashCode();
	}

	public boolean isMutable() {
		return false;
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
		String stringValue = resultSet.getString(names[0]);
		if ( resultSet.wasNull()) {
			return null;
		}
		
		return new ISBN(stringValue);		
	}

	public void nullSafeSet(PreparedStatement statement, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			statement.setNull(index, java.sql.Types.VARCHAR);
		}
		else {
			ISBN isbn = (ISBN) value;
			if (isbn.getValue() == null) {
			    statement.setNull(index, java.sql.Types.VARCHAR);
			} else {
			    statement.setString(index, isbn.getValue());    
			}			
		}		
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}


	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
		return ISBN.class;
	}

	public int[] sqlTypes() {
		return new int [] { java.sql.Types.VARCHAR };
	}



}
