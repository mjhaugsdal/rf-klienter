package io.github.mjhaugsdal.kith.xml.common;

import no.kith.xmlstds.msghead._2006_05_24.CV;

public class CVBuilder {

    private CVBuilder() {
        //
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String v;
        private String dn;
        private String s;
        private String ot;

        public Builder withV(String v) {
            this.v = v;
            return this;
        }

        public Builder withDn(String dn) {
            this.dn = dn;
            return this;
        }

        public Builder withS(String s) {
            this.s = s;
            return this;
        }

        public Builder withOt(String ot) {
            this.ot = ot;
            return this;
        }

        public CV build() {
            return CV.CVBuilder()
                    .withV(v)
                    .withS(s)
                    .withDn(dn)
                    .withOt(ot)
                    .build();
        }
    }
}
