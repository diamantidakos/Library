package com.mgiandia.library.domain;

import org.junit.Assert;
import org.junit.Test;

import com.mgiandia.library.contacts.Address;
import com.mgiandia.library.contacts.EmailAddress;
import com.mgiandia.library.contacts.TelephoneNumber;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class PublisherTest
{
    @Test
    public void memberFunctions()
    {
        Publisher publisher = new Publisher();
        publisher.setName("Name");
        publisher.setTelephone(new TelephoneNumber("123"));
        publisher.setEMail(new EmailAddress("test@test.com"));
        publisher.setAddress(new Address());

        Assert.assertEquals("Name", publisher.getName());
        Assert.assertEquals(new TelephoneNumber("123"), publisher.getTelephone());
        Assert.assertEquals(new EmailAddress("test@test.com"), publisher.getEMail());
        Assert.assertEquals(new Address(), publisher.getAddress());
        Assert.assertEquals(0, publisher.getBooks().size());
    }
}
