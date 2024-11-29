package com.backend.api_medic.infrastructure.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import com.backend.api_medic.domain.model.Credential;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String jwtSecret = "ajksdbkajsd9uoq2308uiubsfd8923jbosdf80u23jbsdfsd";
    private final int jwtExpiration = 120000; // 1800000 for 30 minutes to expire and 120000 for only two minutes

    public String generateToken(Credential credential) throws JOSEException {
        Integer employeeId = credential.getEmployeeId();
        String username = credential.getUsername();
        String role = credential.getRole();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        // Generate claims on JWT Token
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(username)
                .claim("e", employeeId)
                .claim("role", role)
                .issueTime(now)
                .expirationTime(expiryDate)
                .build();

        // Generate JWS Header with HS256
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        // Generate JWS Object
        SignedJWT signedJWT = new SignedJWT(header, claims);

        // Generate the JWS signer with the secret key
        JWSSigner signer = new MACSigner(jwtSecret.getBytes());

        // Sign the JWT
        signedJWT.sign(signer);

        // Serialize the JWT to a compact string
        return signedJWT.serialize();
    }

    public boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            // Generate the JWS verifier with the secret key
            JWSVerifier verifier = new MACVerifier(jwtSecret.getBytes());

            // Check signature and expiration
            return signedJWT.verify(verifier) && !isTokenExpired(signedJWT);
        } catch (JOSEException | ParseException e) {
            return false;
        }
    }

    private boolean isTokenExpired(SignedJWT signedJWT) throws ParseException {
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        return expirationTime.before(new Date());
    }

    public String getUsernameFromToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return (String) signedJWT.getJWTClaimsSet().getClaim("sub");
    }

    public Long getEmployeeIdFromToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return (Long) signedJWT.getJWTClaimsSet().getClaim("e");
    }

    public String getRoleFromToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return (String) signedJWT.getJWTClaimsSet().getClaim("role");
    }

}