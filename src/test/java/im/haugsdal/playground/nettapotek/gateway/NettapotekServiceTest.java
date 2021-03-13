package im.haugsdal.playground.nettapotek.gateway;

import im.haugsdal.playground.Application;
//import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
//import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.spec.X509EncodedKeySpec;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.cxf.rs.security.jose.jwt.JwtToken;
import org.bouncycastle.util.encoders.Base64;

public class NettapotekServiceTest {

    @org.testng.annotations.Test
    public void testPostM9na1() {
    }

    @org.testng.annotations.Test
    public void testHello() throws Exception {
        startServer();
        Client client = ClientBuilder.newBuilder().newClient();
        WebTarget target = client.target("http://localhost:9000/");
        Response response = target.path("nettapotek").
                path("hello").
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Response.class);
        System.out.println(response.readEntity(String.class));
    }

    public void startServer() throws Exception {
        Application.main(null);
    }
    @org.testng.annotations.Test
    public void testJwtCreate(){
        //TokenCreator tokenCreator = new TokenCreator();
        JwtToken token = TokenCreator.createJwt();

        System.out.println("token");
    }


    /**
     * A sample program that creates a JWT token,
     * uses the private key to encrypt it and uses
     * the public key (the exported certificate)
     * to decrypt the token.
     *
     *
     * */
    @org.testng.annotations.Test
    public void testJwtCreateString() throws Exception {
        //TokenCreator tokenCreator = new TokenCreator();
        String token = TokenCreator.createJwtString();


        //PublicKey publicKey = TokenCreator.loadPublicKey("na-cert.cer");
        File f = new File("whatsdis.pem");
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int) f.length()];
        dis.readFully(keyBytes);
        dis.close();

        String publicKeyPEM = new String(keyBytes);
        //String temp = new String(keyBytes);

        byte[] decoded = Base64.decode(publicKeyPEM);

        X509EncodedKeySpec spec =
            new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey test = kf.generatePublic(spec);
        
        KeyStore trustStore = KeyStore.getInstance("JKS");

        InputStream is = new FileInputStream("trustStore.jks");
        trustStore.load(is, "testtest".toCharArray());

        Certificate cert = trustStore.getCertificate("testtrust");


        PublicKey publicKey = cert.getPublicKey();


        Jws<Claims> x = Jwts.parser()
            .setSigningKey(publicKey)
            .parseClaimsJws(token);

        String id = x.getBody().getId();
        System.out.println("id: " + id);
        System.out.println("audience: " + x.getBody().getAudience());
        System.out.println("audience: " + x.getBody().getSubject());
        System.out.println("token");
    }


}