package im.haugsdal.playground.nettapotek.gateway;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.cxf.rs.security.jose.jwt.JwtClaims;
import org.apache.cxf.rs.security.jose.jwt.JwtConstants;
import org.apache.cxf.rs.security.jose.jwt.JwtToken;

public class TokenCreator {

    public static JwtToken createJwt() {

        // Create the JWT Token
        JwtClaims claims = new JwtClaims();
        claims.setSubject("alice");
        claims.setIssuer("DoubleItSTSIssuer");
        claims.setIssuedAt(Instant.now().getEpochSecond());
        claims.setAudiences(toList("address"));
        JwtToken token = new JwtToken(claims);

        Map<String, Object> properties = new HashMap<>();
        properties.put("rs.security.signature.algorithm", "none");
        properties.put(JwtConstants.JWT_CLAIMS, claims);


        return token;
    }
    private static List<String> toList(String address) {
        return Collections.singletonList(address);
    }

    public static String createJwtString() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {

        String jksPassword = "testtest";

        KeyStore ks  = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream("na-test.jks"), jksPassword.toCharArray());
        Key key = ks.getKey("nav2test", jksPassword.toCharArray());

        String compactJws = Jwts.builder()
            .setSubject("Markus")
            .setAudience("http://127.0.0.1:8080/oidc-client/")
            //.setExpiration()
            .setIssuedAt(new Date())
            .setId("testUserId")
            .signWith(SignatureAlgorithm.RS512, key)
            .compact();

        System.out.println(compactJws);
        return compactJws;
    }

    public static PublicKey loadPublicKey(String filename)
        throws Exception
    {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate cert = cf.generateCertificate(new FileInputStream(filename));
        PublicKey retVal = cert.getPublicKey();
        return retVal;
    }

}
