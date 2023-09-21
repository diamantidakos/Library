package com.mgiandia.library.persistence;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.domain.ISBN;



public class ISBNCustomType implements UserType<ISBN> {

	@Override
	public int getSqlType() {
		return org.hibernate.type.SqlTypes.VARCHAR;
	}

	@Override
	public Class<ISBN> returnedClass() {
		return ISBN.class;
	}

	@Override
	public boolean equals(ISBN x, ISBN y) {
		if ( x == y) return true;
		if ( x== null || y==null) return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(ISBN x) {
		return x.hashCode();
	}

	@Override
	public ISBN nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		String stringValue = rs.getString(position);
		if ( rs.wasNull()) {
			return null;
		}
		ISBN isbn = new ISBN(stringValue);
		return isbn;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, ISBN value, int index, SharedSessionContractImplementor session)
			throws SQLException {
		if (value == null) {
			st.setNull(index, java.sql.Types.VARCHAR);
		}
		else {
			ISBN isbn = (ISBN) value;
			if (isbn.getValue() == null) {
				st.setNull(index, java.sql.Types.VARCHAR);
			}
			st.setString(index, isbn.getValue());
		}	
		
	}

	@Override
	public ISBN deepCopy(ISBN value) {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(ISBN value) {
		return value;
	}

	@Override
	public ISBN assemble(Serializable cached, Object owner) {
		return (ISBN) cached;
	}

	
	


}
