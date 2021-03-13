package im.haugsdal.playground.nettapotek.handler;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import no.kith.xmlstds.apprec._2004_11_21.AppRec;
import no.kith.xmlstds.eresept.m9na1._2016_06_06.M9NA1;
import no.kith.xmlstds.eresept.m9na2._2016_10_26.M9NA2;
import no.kith.xmlstds.eresept.m9na3._2016_06_06.M9NA3;
import no.kith.xmlstds.eresept.m9na4._2016_06_06.M9NA4;
import no.kith.xmlstds.msghead._2006_05_24.MsgHead;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class FooContextResolver implements ContextResolver<JAXBContext> {

    private JAXBContext jc;

    public FooContextResolver() {
        try {
            jc = JAXBContext.newInstance("no.kith.xmlstds");
        } catch(JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public JAXBContext getContext(Class<?> clazz) {
        if(MsgHead.class == clazz) {
            return jc;
        }
        return null;
    }

}