package com.mgiandia.library.domain;


public class LoanedState extends ItemObjectState {

	@Override
	void available(Item item) {
		item.setObjectState(new AvailableState());
	}

	@Override
	void lost(Item item) {
		item.setObjectState(new LostState());
	}
	
    @Override
    ItemState getState() {
        return ItemState.LOANED;
    }
}
