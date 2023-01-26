package ru.policlinic.server.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

//    private static final String SECRET_KEY = "2B4B6250655368566D597133743677397A244326462948404D635166546A576E"; // Генерация секретного 256-битного hex-ключа на сайте: https://www.allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public JwtService() {
        // Алгоритм создания кодировки RSA с приватным ключом
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyPairGenerator keyGenerator = null;
        try {
            keyGenerator = KeyPairGenerator.getInstance("RSA", "BC");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        keyGenerator.initialize(2048);

        KeyPair kp = keyGenerator.genKeyPair();
        publicKey = (PublicKey) kp.getPublic();
        privateKey = (PrivateKey) kp.getPrivate();
    }

    public String extractUsername(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws NoSuchAlgorithmException, NoSuchProviderException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }

    public String generateToken(UserDetails userDetails) throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) throws NoSuchAlgorithmException, NoSuchProviderException {
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    private Claims extractAllClaims(String token) throws NoSuchAlgorithmException, NoSuchProviderException {
        return Jwts
                .parserBuilder()
                .setSigningKey(privateKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    private Key getSignInKey() throws NoSuchAlgorithmException, NoSuchProviderException {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA", "BC");
//        keyGenerator.initialize(256);
//
//        KeyPair kp = keyGenerator.genKeyPair();
//        PublicKey publicKey = (PublicKey) kp.getPublic();
//        PrivateKey privateKey = (PrivateKey) kp.getPrivate();
//
//        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public void createPemFiles() throws Exception {
//        try {
//            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
//            SecureRandom random = SecureRandom.getInstanceStrong();
//            keyGen.initialize(256, random);
//
//            KeyPair pair = keyGen.generateKeyPair();
//            PrivateKey priv = pair.getPrivate();
//            PublicKey pub = pair.getPublic();
//
//            byte[] encPriv = priv.getEncoded();
//            FileOutputStream privfos = new FileOutputStream("C://RPBD_RGR//server//src//main//resources//certs//privateKey.pem");
//            privfos.write(encPriv);
//            privfos.close();
//
//            byte[] encPub = pub.getEncoded();
//            FileOutputStream pubfos = new FileOutputStream("C://RPBD_RGR//server//src//main//resources//certs//publicKey.pem");
//            pubfos.write(encPub);
//            pubfos.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
