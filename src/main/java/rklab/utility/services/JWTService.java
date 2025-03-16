package rklab.utility.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTService {


    /**
     * Generate token string.
     *
     * @param jwtConfiguration the jwt configuration
     * @param userDetails      the user details
     * @return the string
     */
    public String generateToken(
            JwtConfiguration jwtConfiguration,
            UserDetails userDetails
    ){
        return Jwts.builder()
                .claims(new HashMap<>())
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(jwtConfiguration.getExpiration())
                .signWith(getSignKey(jwtConfiguration.getSecretKey()))
                .compact();
    }

    private SecretKey getSignKey(String secret){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Extract username string.
     *
     * @param token            the token
     * @param jwtConfiguration the jwt configuration
     * @return the string
     */
    public String extractUsername(
            String token,
            JwtConfiguration jwtConfiguration
    ) {
        return extractClaim(token, Claims::getSubject, jwtConfiguration);
    }

    private <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver,
            JwtConfiguration jwtConfiguration
    ) {
        final Claims claims = extractAllClaims(token, jwtConfiguration);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, JwtConfiguration jwtConfiguration) {
        return Jwts.parser()
                .verifyWith(getSignKey(jwtConfiguration.getSecretKey()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
