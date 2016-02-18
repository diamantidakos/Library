package com.mgiandia.library.ui.borrower;

import java.util.List;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.ui.View;

public interface BorrowerListView extends View {
    void setPresenter(BorrowerListPresenter presenter);
    void setBorrowers(List<Borrower> borrowers);
    Borrower getSelectedBorrower();
}
