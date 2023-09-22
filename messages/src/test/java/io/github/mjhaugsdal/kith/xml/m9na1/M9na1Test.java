package io.github.mjhaugsdal.kith.xml.m9na1;

import io.github.mjhaugsdal.kith.xml.common.CVBuilder;
import io.github.mjhaugsdal.kith.xml.common.IdentBuilder;
import io.github.mjhaugsdal.kith.xml.common.SenderBuilder;
import org.junit.jupiter.api.Test;

class M9na1Test {

    @Test
    void buildMessagetest() {
        var m9na1 = M9na1.m9naBuilder()
                .withSender(SenderBuilder.builder()
                        .withIdent(IdentBuilder.builder()
                                .withId("100149184")
                                .withCV(CVBuilder.builder()
                                        .withV("ENH")
                                        .withDn("Organisasjonsnummeret i Enhetsregister (Brønnøysund)")
                                        .withS("2.16.578.1.12.4.1.1.9051")
                                        .build())
                                .build())
                        .withIdent(IdentBuilder.builder()
                                .withId("100100185")
                                .withCV(CVBuilder.builder()
                                        .withV("LOK")
                                        .withDn("Lokal identifikator uten nærmere angivelse")
                                        .withS("2.16.578.1.12.4.1.1.9051")
                                        .build())
                                .build())
                        .withIdent(IdentBuilder.builder()
                                .withId("8069")
                                .withCV(CVBuilder.builder()
                                        .withV("AKO")
                                        .withDn("Apotekets konsesjonsnummer")
                                        .withS("2.16.578.1.12.4.1.1.9051")
                                        .build())
                                .build())
                        .withIdent(IdentBuilder.builder()
                                .withId("8094938")
                                .withCV(CVBuilder.builder()
                                        .withV("HER")
                                        .withDn("Identifikator fra Helsetjenesteenhetsregisteret (HER-id)")
                                        .withS("2.16.578.1.12.4.1.1.9051")
                                        .build())
                                .build())
                        .build())

                .withReceiver()
                .withRefNr("123456789")
                .build();

        System.out.println("!");

    }

}