package com.mgiandia.library.persistence;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.contacts.ZipCode;

public class EMailCustomType implements UserType<EmailAddress> {

	@Override
	public int getSqlType() {
		return org.hibernate.type.SqlTypes.VARCHAR;
	}

	@Override
	public Class<EmailAddress> returnedClass() {
		return EmailAddress.class;
	}

	@Override
	public boolean equals(EmailAddress x, EmailAddress y) {
		if ( x == y) return true;
		if ( x== null || y==null) return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(EmailAddress x) {
		return x.hashCode();
	}

	@Override
	public EmailAddress nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		String stringValue = rs.getString(position);
		if ( rs.wasNull()) {
			return null;
		}
		EmailAddress email = new EmailAddress(stringValue);
		return email;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, EmailAddress value, int index,
			SharedSessionContractImplementor session) throws SQLException {
		if (value == null) {
			st.setNull(index, java.sql.Types.VARCHAR);
		}
		else {
			EmailAddress email = (EmailAddress) value;
			if (email.getAddress() == null) {
				st.setNull(index, java.sql.Types.VARCHAR);
			}
			st.setString(index, email.getAddress());
		}		
		
		
	}

	@Override
	public EmailAddress deepCopy(EmailAddress value) {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(EmailAddress value) {
		return value;
	}

	@Override
	public EmailAddress assemble(Serializable cached, Object owner) {
		return (EmailAddress) cached;
	}
	


}
