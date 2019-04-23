package com.company.demo

/**
* This example domain class.
* Author: Prominic -Bing Li
* Data: 2019-04-21
*/

class AnagraphicDomain {

    
    String firstName
    String surname

    Date date
    Long number
    Double db
    BigDecimal decimal
    Boolean test
    char ch = 'c'

  

    static constraints = {
        firstName nullable: false, blank: false
        surname nullable: false, blank: false

        date nullable: true
        number nullable: true
        db nullable: true
        decimal nullable: true
        test nullable: true
    }
}
