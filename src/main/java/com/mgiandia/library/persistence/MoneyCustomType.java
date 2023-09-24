package com.mgiandia.library.persistence;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mgiandia.library.util.Money;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.ValueAccess;
import org.hibernate.usertype.CompositeUserType;

public class MoneyCustomType implements CompositeUserType<Money>{

	public static class MoneyMapper {
		BigDecimal amount;
		String currency;
	}
	
	@Override
	public Object getPropertyValue(Money component, int property) throws HibernateException {
		switch ( property ) {
			case 0:
				return component.getAmount();
			case 1:
				return component.getCurrency();
		}
		return null;
	}

	@Override
	public Money instantiate(ValueAccess values, SessionFactoryImplementor sessionFactory) {
		BigDecimal amount = values.getValue(0, BigDecimal.class);
		String currency = values.getValue(1, String.class);
		return new Money(amount, currency);
	}

	@Override
	public Class<?> embeddable() {
		return MoneyMapper.class;
	}

	@Override
	public Class<Money> returnedClass() {
		return Money.class;
	}

	@Override
	public boolean equals(Money x, Money y) {
		if ( x == y) return true;
		if ( x== null || y==null) return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(Money x) {
		return x.hashCode();
	}

	@Override
	public Money deepCopy(Money value) {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Money value) {
		return value;
	}

	@Override
	public Money assemble(Serializable cached, Object owner) {
		return (Money) cached;
	}

	@Override
	public Money replace(Money detached, Money managed, Object owner) {
		return detached;
	}

	
	
}
