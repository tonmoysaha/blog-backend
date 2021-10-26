package com.square.health.jwt;
import com.square.health.model.Admin;
import com.square.health.model.Blogger;
import com.square.health.repositoy.AdminRepository;
import com.square.health.repositoy.BloggerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    private Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.signing.key.secret}")
    private String secret;

    @Value("${jwt.token.expiration.in.seconds}")
    private Long expiration;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BloggerRepository bloggerRepository;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }


    public String generateTokenForAdmin(UserDetails userDetails) {
        Admin admin = this.adminRepository.findByEmail(userDetails.getUsername());
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        claims.put("email", admin.getEmail());
        claims.put("adminId", admin.getId());
        claims.put("adminName", admin.getUserName());
        claims.put("create", admin.getCreated().toString());
        claims.put("AdminUserType", "ADMIN");
        return doGenerateToken(claims, userDetails.getUsername());
    }


    public String generateTokenForBlogger(UserDetails userDetails) {
        Optional<Blogger> optionalBlogger = this.bloggerRepository.findByEmail(userDetails.getUsername());
        Blogger blogger = optionalBlogger.get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        claims.put("email", blogger.getEmail());
        claims.put("bloggerId", blogger.getId());
        claims.put("bloggerName", blogger.getUserName());
        claims.put("status", blogger.getStatus());
        claims.put("create", blogger.getCreated().toString());
        claims.put("BloggerUserType", "BLOGGER");
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
                .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        String userName = this.getUserName(token);
        final String username = getUsernameFromToken(token);
        return (username.equals(userName) && !isTokenExpired(token));
    }

    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }
}
