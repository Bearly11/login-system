package com.phanith.loginapp.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${SECRET_KEY}")
    private String secretKey;
    @Value("${ACCESS_EXPIRATION}")
    private long accessExpiration;
    @Value("${REFRESH_EXPIRATION}")
    private long refreshExpiration;

    // extract token
    public String extractUsername(String token) {
        return extraClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extraClaims(token, Claims::getExpiration);
    }

    public <T> T extraClaims(String token, Function<Claims, T> resolver){
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    // Generate token

    public String generateAccessToken(UserDetails userDetails){
        return buildToken(Map.of(),userDetails, accessExpiration);
    }
    public String generateRefreshToken(UserDetails userDetails){
        return buildToken(Map.of(),userDetails, refreshExpiration);
    }

    public String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSigInKey())
                .compact();
    }


    private Key getSigInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // validate token
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }

}
