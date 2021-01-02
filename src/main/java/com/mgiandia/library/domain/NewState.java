package com.mgiandia.library.domain;


public class NewState extends ItemObjectState {

	@Override
	void available(Item item) {
		item.setObjectState(new AvailableState());
	}
	
    @Override
    ItemState getState() {
        return ItemState.NEW;
    }

    
}
