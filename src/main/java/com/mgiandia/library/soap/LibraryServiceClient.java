package com.mgiandia.library.soap;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


public class LibraryServiceClient {
    public static void main(String args[ ]) throws Exception {
        URL url = new URL("http://localhost:9877/library?wsdl");

        // Το QName της υπηρεσίας
        // Η πρώτη παράμετος είναι το URI
        // Η δεύτερη παράμετρος είναι το όνομα της υπηρεσίας στο WSDL
        QName qname = new QName("http://ws.library.mgiandia.com/", "LibraryServiceImplService");

        // Δημιουρία της υπηρεσίας
        Service service = Service.create(url, qname);

        // Λαμβάνουμε το endpoint interface
        LibraryService endpointInterface = service.getPort(LibraryService.class);

        // Δανεισμός του αντιτύπου με id=2
        System.out.println(endpointInterface.loanItem(2, 2));
       
        // Λήψη και εμφάνιση όλων των αντιτύπων
        List<ItemInfo> items = endpointInterface.getAllItems();
        for(ItemInfo item : items) {
            System.out.println("Item No: " + item.getItemNo());
            System.out.println("Status: " + item.getStatus());
            System.out.println("Title: " + item.getTitle());
            System.out.println("Isbn: " + item.getIsbn());
            System.out.println("Authors: " + item.getAuthors());
            System.out.println("Publication: " + item.getPublication());
            System.out.println("Publication year: " + item.getYear());
            System.out.println();
        }
        
        // Επιστροφή του αντιτύπου με id=2
        endpointInterface.returnItem(2);
   }

}
