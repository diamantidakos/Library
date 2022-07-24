package com.mgiandia.library.soap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.EntityManager;

import com.mgiandia.library.LibraryException;
import com.mgiandia.library.domain.Item;
import com.mgiandia.library.service.LoanService;
import com.mgiandia.library.service.ReturnService;
import com.mgiandia.library.util.Money;

@WebService(endpointInterface = "com.mgiandia.library.soap.LibraryService")
public class LibraryServiceImpl implements LibraryService {

	@Inject
	LoanService loanService;
	
	@Inject
	ReturnService returnService;
	
	@Inject
	EntityManager em;
	
    public LocalDate loanItem(int borrowerNo, int itemNo)
            throws BorrowerNotFoundException, CanNotBorrowException {
        boolean found = loanService.findBorrower(borrowerNo);
        if (!found) {
            throw new BorrowerNotFoundException(borrowerNo);
        }

        LocalDate returnDate = loanService.borrow(itemNo);
        if (returnDate == null) {
            throw new CanNotBorrowException(borrowerNo);
        }

        return returnDate;
    }


    public MonetaryAmount returnItem(int itemNo) throws LoanNotFoundException {
        try {
            Money result = returnService.returnItem(itemNo);
            return result == null ? null : new MonetaryAmount(result); 
        } catch (LibraryException e) {
            throw new  LoanNotFoundException (itemNo);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<ItemInfo> getAllItems() {
        List<ItemInfo> result = new ArrayList<ItemInfo>();
		List<Item> items = em.createQuery("select i from Item i").getResultList();
        for(Item item : items) {
            result.add(new ItemInfo(item));
        }
        return result;
    }



    public ItemInfo getItemInfo(int itemNo) {
        Item item = em.find(Item.class, itemNo);
        ItemInfo itemInfo = new ItemInfo(item);
        return itemInfo;
    }
    
    
    
}
