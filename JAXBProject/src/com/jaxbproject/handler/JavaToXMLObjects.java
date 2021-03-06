package com.jaxbproject.handler;

import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.jaxbproject.generated.ExpenseT;
import com.jaxbproject.generated.ItemListT;
import com.jaxbproject.generated.ItemT;
import com.jaxbproject.generated.ObjectFactory;
import com.jaxbproject.generated.UserT;

public class JavaToXMLObjects

{
    public static void main(String[] args) throws JAXBException
    {

        ObjectFactory factory = new ObjectFactory();

        UserT user = factory.createUserT();
        user.setUserName("Sanaulla");
        ItemT item = factory.createItemT();
        item.setItemName("Seagate External HDD");
        item.setPurchasedOn("August 24, 2010");
        item.setAmount(new BigDecimal("6776.5"));

        ItemListT itemList = factory.createItemListT();
        itemList.getItem().add(item);

        ExpenseT expense = factory.createExpenseT();
        expense.setUser(user);
        expense.setItems(itemList);

        JAXBContext context = JAXBContext.newInstance("com.jaxbproject.generated");
        JAXBElement<ExpenseT> element = factory.createExpenseReport(expense);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
        marshaller.marshal(element,System.out);
    }

}
