package com.mgiandia.library.ui.borrower;

import com.mgiandia.library.ui.View;

public interface BorrowerView extends View {
    void setPresenter(BorrowerPresenter presenter);
    int getBorrowerNo();
    void setBorrowerNo(int borrowerNo);
    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
}
