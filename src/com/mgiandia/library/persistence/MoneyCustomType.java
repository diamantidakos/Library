package com.mgiandia.library.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import com.mgiandia.library.util.Money;

public class MoneyCustomType implements UserType{

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
		BigDecimal amount = resultSet.getBigDecimal(names[0]);		
		if ( resultSet.wasNull()) {
			return null;
		}
		
		String currency = resultSet.getString(names[1]);		
		Money money = new Money(amount, Currency.getInstance(currency));				
		return money;
	}

	public void nullSafeSet(PreparedStatement statement, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			statement.setNull(index, java.sql.Types.DECIMAL);
			statement.setNull(index+1, java.sql.Types.VARCHAR);
		}
		else {
			Money money = (Money) value;
			if (money.getAmount() == null ) {
	            statement.setNull(index, java.sql.Types.DECIMAL);
	            statement.setNull(index+1, java.sql.Types.VARCHAR);			    
			} else {
	            statement.setBigDecimal(index, money.getAmount());
	            statement.setString(index+1, money.getCurrency().getCurrencyCode());			    
			}
		}		
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}


	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
		return Money.class;
	}

	public int[] sqlTypes() {
		return new int [] { java.sql.Types.DECIMAL, java.sql.Types.VARCHAR };
	}

}
