package com.mgiandia.library.representation;

import com.mgiandia.library.domain.ItemState;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ItemRepresentation {
	public Integer itemNumber;
	public BookRepresentation book;
	public ItemState state;
}
