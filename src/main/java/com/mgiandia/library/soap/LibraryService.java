package com.mgiandia.library.soap;

import java.time.LocalDate;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


@WebService
@SOAPBinding(style = Style.DOCUMENT, 
        parameterStyle=javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED)
public interface LibraryService {
    
    @WebMethod
    @WebResult(partName = "dueDate")
    LocalDate loanItem(
            @WebParam(name="borrowerNo") int borrowerNo, 
            @WebParam(name="itemNo") int itemNo)
        throws BorrowerNotFoundException, CanNotBorrowException;
    
    @WebMethod
    @WebResult(partName = "fine")
    MonetaryAmount returnItem(
            @WebParam(name="itemNo") int itemNo) 
        throws LoanNotFoundException;
    
    @WebMethod
    @WebResult(partName="itemList")
    List<ItemInfo> getAllItems();

    @WebMethod
    @WebResult(partName="item")
    ItemInfo getItemInfo( @WebParam(name="itemNo") int itemNo);
    
}
