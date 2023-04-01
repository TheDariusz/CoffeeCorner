package com.thedariusz.coffeecorner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ReceiptTest extends BaseOrder{
    
    @Test
    void shouldProduceReceipt() {
        Receipt receipt = Receipt.build(testOrder);
        String receiptTimeStamp = receipt.getReceiptTimeStamp();
        
        String expectedReceipt = """
                
                |--------------------------------------------------------------|
                |Coffee Corner Lmt.                                            |
                |"""
                +receiptTimeStamp+"                                 |\n"+
                """
                |--------------------------------------------------------------|
                |Products ordered:                                             |
                |Small coffee:                                             2.50|
                |Medium coffee:                                            3.00|
                |Large coffee:                                             5.50|
                |Large coffee with foamed milk with special roast coffee:  6.90|
                |Freshly squeezed orange juice:                            3.95|
                |Bacon Roll:                                               4.50|
                |--------------------------------------------------------------|
                |Discounts:                                                    |
                |Free Freshly squeezed orange juice:                      -3.95|
                |Free foamed milk:                                        -0.50|
                |--------------------------------------------------------------|
                |Summary:                                                 21.90|
                |--------------------------------------------------------------|""";

        Assertions.assertThat(receipt.print()).isEqualTo(expectedReceipt);
    }

}