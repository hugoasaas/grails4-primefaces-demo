package com.company.demo

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
