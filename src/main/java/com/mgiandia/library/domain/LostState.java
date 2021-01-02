package com.mgiandia.library.domain;


public class LostState extends ItemObjectState{
    @Override
    ItemState getState() {
        return ItemState.LOST;
    }
}
