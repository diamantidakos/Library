package com.mgiandia.library.persistence;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.mgiandia.library.contacts.ZipCode;

public class ZipCodeCustomType implements UserType<ZipCode> {

	@Override
	public int getSqlType() {
		return org.hibernate.type.SqlTypes.VARCHAR;
	}

	@Override
	public Class<ZipCode> returnedClass() {
		return ZipCode.class;
	}

	@Override
	public boolean equals(ZipCode x, ZipCode y) {
		if ( x == y) return true;
		if ( x== null || y==null) return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(ZipCode x) {
		return x.hashCode();
	}

	@Override
	public ZipCode nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		String stringValue = rs.getString(position);
		if ( rs.wasNull()) {
			return null;
		}
		ZipCode zipCode = new ZipCode(stringValue);
		return zipCode;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, ZipCode value, int index, SharedSessionContractImplementor session)
			throws SQLException {
		if (value == null) {
			st.setNull(index, java.sql.Types.VARCHAR);
		}
		else {
			ZipCode zipCode = (ZipCode) value;
			if (zipCode.getCode() == null) {
				st.setNull(index, java.sql.Types.VARCHAR);
			}
			st.setString(index, zipCode.getCode());
		}		
		
	}

	@Override
	public ZipCode deepCopy(ZipCode value) {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(ZipCode value) {
		return (Serializable) value;
	}

	@Override
	public ZipCode assemble(Serializable cached, Object owner) {
		return (ZipCode) cached;
	}
	
	
}