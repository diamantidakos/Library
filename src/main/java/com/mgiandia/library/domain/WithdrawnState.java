package com.mgiandia.library.domain;


public class WithdrawnState extends ItemObjectState {
    @Override
    ItemState getState() {
        return ItemState.WITHDRAWN;
    }

}
