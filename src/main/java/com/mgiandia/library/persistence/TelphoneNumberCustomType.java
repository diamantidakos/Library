package com.mgiandia.library.persistence;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.contacts.TelephoneNumber;


public class TelphoneNumberCustomType implements UserType<TelephoneNumber>{

	@Override
	public int getSqlType() {
		return org.hibernate.type.SqlTypes.VARCHAR;
	}

	@Override
	public Class<TelephoneNumber> returnedClass() {
		return TelephoneNumber.class;
	}

	@Override
	public boolean equals(TelephoneNumber x, TelephoneNumber y) {
		if ( x == y) return true;
		if ( x== null || y==null) return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(TelephoneNumber x) {
		return x.hashCode();
	}

	@Override
	public TelephoneNumber nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session,
			Object owner) throws SQLException {
		String stringValue = rs.getString(position);
		if ( rs.wasNull()) {
			return null;
		}
		TelephoneNumber number = new TelephoneNumber(stringValue);
		return number;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, TelephoneNumber value, int index,
			SharedSessionContractImplementor session) throws SQLException {
		if (value == null) {
			st.setNull(index, java.sql.Types.VARCHAR);
		}
		else {
			TelephoneNumber email = (TelephoneNumber) value;
			if (email.getTelephoneNumber() == null) {
				st.setNull(index, java.sql.Types.VARCHAR);
			}
			st.setString(index, email.getTelephoneNumber());
		}			
	}

	@Override
	public TelephoneNumber deepCopy(TelephoneNumber value) {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(TelephoneNumber value) {
		return value;
	}

	@Override
	public TelephoneNumber assemble(Serializable cached, Object owner) {
		return (TelephoneNumber) cached;
	}

	
}
