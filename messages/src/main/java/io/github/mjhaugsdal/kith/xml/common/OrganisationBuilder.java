package io.github.mjhaugsdal.kith.xml.common;

import no.kith.xmlstds.msghead._2006_05_24.Ident;
import no.kith.xmlstds.msghead._2006_05_24.Organisation;
import no.kith.xmlstds.msghead._2006_05_24.TeleCom;

import java.util.ArrayList;
import java.util.List;

public class OrganisationBuilder {


    private OrganisationBuilder() {
        //
    }

    public static Builder myOrganisationBuilder() {
        return new Builder();
    }


    public static class Builder {

        private String name;

        private final List<Ident> idents = new ArrayList<>();

        private final List<TeleCom> teleComs = new ArrayList<>();

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withIdent(Ident ident) {
            idents.add(ident);
            return this;
        }

        public Builder withTelecom(TeleCom telecom) {
            teleComs.add(telecom);
            return this;
        }

        public Organisation build() {
            validate();
            return Organisation.organisationBuilder()
                    .withOrganisationName(name)
                    .withIdent(idents)
                    .withTeleCom(teleComs)
                    .build();
        }

        private void validate() {
            if (teleComs.isEmpty())
                throw new RuntimeException(); //TODO validation exception
            if (idents.isEmpty()) {
                throw new RuntimeException(); //TODO validation exception
            }
        }
    }
}
