package io.github.mjhaugsdal.kith.xml.common;

import no.kith.xmlstds.msghead._2006_05_24.Address;

public class MyAddress {


    public MyAddress(MyAddressBuilder myAddressBuilder) {

        var address = Address.addressBuilder()
                .build();
    }

    public static class MyAddressBuilder {

        public MyAddress build() {
            return new MyAddress(this);
        }
    }
}
