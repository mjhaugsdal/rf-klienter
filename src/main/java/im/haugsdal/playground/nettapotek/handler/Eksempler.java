package im.haugsdal.playground.nettapotek.handler;

public class Eksempler {

    public static final String M9NA1="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<MsgHead xmlns=\"http://www.kith.no/xmlstds/msghead/2006-05-24\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kith.no/xmlstds/msghead/2006-05-24 ../../../../../kith/felles/MsgHead-v1_2.xsd\">\n" +
            "  <MsgInfo>\n" +
            "    <Type V=\"ERM9NA1\" DN=\"Forespørsel om reseptliste fra nettkunde\"/>\n" +
            "    <MIGversion>v1.2 2006-05-24</MIGversion>\n" +
            "    <GenDate>2020-11-24T13:32:41.990+01:00</GenDate>\n" +
            "    <MsgId>c48fad2e-e589-4d41-9961-91693908ad90</MsgId>\n" +
            "    <Sender>\n" +
            "      <Organisation>\n" +
            "        <OrganisationName>æøåÆØÅ®ÁáČčĐđŊ200ŧŽž</OrganisationName>\n" +
            "        <Ident>\n" +
            "          <Id>983044778</Id>\n" +
            "          <TypeId V=\"ENH\" S=\"2.16.578.1.12.4.1.1.9051\" DN=\"Organisasjonsnummeret i Enhetsregister\"/>\n" +
            "        </Ident>\n" +
            "        <Ident>\n" +
            "          <Id>94181</Id>\n" +
            "          <TypeId V=\"HER\" DN=\"Idenfikator fra Helsetjenesteenhetsregisteret\" S=\"2.16.578.1.12.4.1.1.9051\"/>\n" +
            "        </Ident>\n" +
            "        <Ident>\n" +
            "          <Id>1463</Id>\n" +
            "          <TypeId S=\"2.16.578.1.12.4.1.1.9051\" V=\"AKO\" DN=\"Idenfikator fra Helsetjenesteenhetsregisteret\"/>\n" +
            "        </Ident>\n" +
            "      </Organisation>\n" +
            "    </Sender>\n" +
            "    <Receiver>\n" +
            "      <Organisation>\n" +
            "        <OrganisationName>Reseptformidleren</OrganisationName>\n" +
            "        <Ident>\n" +
            "          <Id>999888777</Id>\n" +
            "          <TypeId V=\"ENH\" S=\"2.16.578.1.12.4.1.1.9051\" DN=\"Organisasjonsnummeret i Enhetsregister (Brønnøysund)\"/>\n" +
            "        </Ident>\n" +
            "        <Ident>\n" +
            "          <Id>666.666</Id>\n" +
            "          <TypeId V=\"HER\" DN=\"Idenfikator fra Helsetjenesteenhetsregisteret\" S=\"2.16.578.1.12.4.1.1.9051\"/>\n" +
            "        </Ident>\n" +
            "      </Organisation>\n" +
            "    </Receiver>\n" +
            "    <Patient>\n" +
            "      <FamilyName>Dz</FamilyName>\n" +
            "      <GivenName>Quvdogtfuv</GivenName>\n" +
            "      <Ident>\n" +
            "        <Id>01096200222</Id>\n" +
            "        <TypeId V=\"FNR\" DN=\"Fødselsnummer\" S=\"2.16.578.1.12.4.1.1.8116\"/>\n" +
            "      </Ident>\n" +
            "    </Patient>\n" +
            "  </MsgInfo>\n" +
            "  <Document>\n" +
            "    <RefDoc>\n" +
            "      <MsgType V=\"XML\" DN=\"XML-instans\"/>\n" +
            "      <Content>\n" +
            "        <M9NA1 xmlns=\"http://www.kith.no/xmlstds/eresept/m9na1/2016-06-06\" xmlns:kith=\"http://www.kith.no/xmlstds\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema.xsd\" xsi:schemaLocation=\"http://www.kith.no/xmlstds/eresept/m9na1/2016-06-06 ../../../../../kith/schemas/ER-M9na1-2016-06-06.xsd\">\n" +
            "          <IdKunde xmlns:fk1=\"http://www.kith.no/xmlstds/felleskomponent1\">\n" +
            "            <fk1:Id>01096200222</fk1:Id>\n" +
            "            <fk1:TypeId V=\"FNR\" DN=\"Fødselsnummer\" S=\"2.16.578.1.12.4.1.1.8116\"/>\n" +
            "          </IdKunde>\n" +
            "          <!--RefNr>22116834832</RefNr-->\n" +
            "        </M9NA1>\n" +
            "      </Content>\n" +
            "    </RefDoc>\n" +
            "  </Document>\n" +
            "</MsgHead>";

    public static final String M9NA3 = "<MsgHead xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.kith.no/xmlstds/msghead/2006-05-24\">\n" +
        "  <MsgInfo>\n" +
        "    <Type V=\"ERM9NA3\" DN=\"Forespørsel om utvidet informasjon på resept fra nettkunde\"/>\n" +
        "    <MIGversion>v1.2 2006-05-24</MIGversion>\n" +
        "    <GenDate>2021-02-03T18:50:49.5016822+01:00</GenDate>\n" +
        "    <MsgId>e64c501a-5b45-49fc-a542-310cff9b1c18</MsgId>\n" +
        "    <ConversationRef>\n" +
        "      <RefToParent>32882fbc-6b14-4ef7-8f1c-0492e930e958</RefToParent>\n" +
        "      <RefToConversation>10cbcdce-49d7-4bfd-beed-0f32c1b62340</RefToConversation>\n" +
        "    </ConversationRef>\n" +
        "    <Sender>\n" +
        "      <Organisation>\n" +
        "        <OrganisationName>Apotek Laksen</OrganisationName>\n" +
        "        <Ident>\n" +
        "          <Id>982547822</Id>\n" +
        "          <TypeId V=\"ENH\" S=\"2.16.578.1.12.4.1.1.9051\" DN=\"Organisasjonsnummeret i Enhetsregister\"/>\n" +
        "        </Ident>\n" +
        "        <Ident>\n" +
        "          <Id>8095830</Id>\n" +
        "          <TypeId V=\"HER\" S=\"2.16.578.1.12.4.1.1.9051\" DN=\"HER-id\"/>\n" +
        "        </Ident>\n" +
        "        <Ident>\n" +
        "          <Id>8040</Id>\n" +
        "          <TypeId V=\"AKO\" S=\"2.16.578.1.12.4.1.1.9051\" DN=\"Apotekets konsesjonsnummer\"/>\n" +
        "        </Ident>\n" +
        "      </Organisation>\n" +
        "    </Sender>\n" +
        "    <Receiver>\n" +
        "      <Organisation>\n" +
        "        <OrganisationName>Reseptformidleren</OrganisationName>\n" +
        "        <Ident>\n" +
        "          <Id>80624</Id>\n" +
        "          <TypeId V=\"HER\" S=\"2.16.578.1.12.4.1.1.9051\" DN=\"HER-id\"/>\n" +
        "        </Ident>\n" +
        "      </Organisation>\n" +
        "    </Receiver>\n" +
        "    <Patient>\n" +
        "      <Ident>\n" +
        "        <Id>21056917281</Id>\n" +
        "        <TypeId V=\"FNR\" S=\"2.16.578.1.12.4.1.1.8116\" DN=\"Fødselsnummer\"/>\n" +
        "      </Ident>\n" +
        "    </Patient>\n" +
        "  </MsgInfo>\n" +
        "  <Document>\n" +
        "    <RefDoc>\n" +
        "      <MsgType V=\"XML\" DN=\"XML-instans\"/>\n" +
        "      <Content>\n" +
        "        <M9NA3 xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.kith.no/xmlstds/eresept/m9na3/2016-06-06\">\n" +
        "          <IdKunde>\n" +
        "            <Id xmlns=\"http://www.kith.no/xmlstds/felleskomponent1\">21056917281</Id>\n" +
        "            <TypeId xmlns=\"http://www.kith.no/xmlstds/felleskomponent1\" V=\"FNR\" S=\"2.16.578.1.12.4.1.1.8116\" DN=\"Fødselsnummer\"/>\n" +
        "          </IdKunde>\n" +
        "          <ReseptId>bb4bc6d0-db0a-43a2-a442-1ae2622571e8</ReseptId>\n" +
        "        </M9NA3>\n" +
        "      </Content>\n" +
        "    </RefDoc>\n" +
        "  </Document>\n" +
        "</MsgHead>";
}
