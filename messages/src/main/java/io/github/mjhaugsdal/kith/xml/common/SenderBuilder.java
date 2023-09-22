package io.github.mjhaugsdal.kith.xml.common;

import no.kith.xmlstds.msghead._2006_05_24.Ident;
import no.kith.xmlstds.msghead._2006_05_24.Sender;
import no.kith.xmlstds.msghead._2006_05_24.TeleCom;

public class SenderBuilder {

    private SenderBuilder() {
        //
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Ident ident;

        private TeleCom teleCom;

        public Builder withIdent(Ident ident) {
            this.ident = ident;
            return this;
        }

        public Builder withTelecom(TeleCom telecom) {
            this.teleCom = telecom;
            return this;
        }

        public Sender build() {
            return Sender.senderBuilder()
                    .withOrganisation(OrganisationBuilder.myOrganisationBuilder()
                            .withName("Testapotek Monier")
                            .withIdent(ident)
                            .withTelecom(teleCom)
                            .build()
                            .getOrganisation())
                    .build();
        }
    }
}
