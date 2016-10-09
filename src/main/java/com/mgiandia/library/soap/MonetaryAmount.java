package com.mgiandia.library.soap;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.mgiandia.library.util.Money;

@XmlType(name="MonetaryAmount")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MonetaryAmount {
    private BigDecimal amount;
    private String currency;
    
    public MonetaryAmount() {}
    
    public MonetaryAmount(Money money) {
        amount = money.getAmount();
        currency = money.getCurrency().getSymbol();
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    
    public void setCurrency(String currency) {
        this.currency = currency;
    }


    
}
