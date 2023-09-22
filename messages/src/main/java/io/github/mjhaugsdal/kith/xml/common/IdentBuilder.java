package io.github.mjhaugsdal.kith.xml.common;

import no.kith.xmlstds.msghead._2006_05_24.CV;
import no.kith.xmlstds.msghead._2006_05_24.Ident;

public class IdentBuilder {

    private IdentBuilder() {
        //
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private CV cv;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withCV(CV cv) {
            this.cv = cv;
            return this;
        }


        public Ident build() {
//            validate(); // TODO
            return Ident.identBuilder()
                    .withId(id)
                    .withTypeId(cv)
                    .build();
        }
    }
}
