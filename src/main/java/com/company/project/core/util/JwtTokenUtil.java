package com.company.project.core.util;

import com.company.project.configuration.WebMvcConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package com.company.project.core.util
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-07-13
 */
@Component
public class JwtTokenUtil {
    private final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    private static final String SECRET = "my_secret"; //密钥
    private static final Long EXPIRATION = 60*60L; //Token有效时间(1小时);


    /**
     * 方法中默认没有设置Header,设置了负载的用户信息claims,签发时间,过期时间
     * @param user
     * @return
     */
    public String generateToken(JwtUser user){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("username",user.getUsername());
        claims.put("email",user.getEmail());
        claims.put("enabled",user.isEnabled());
        final Date currentDate = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(calculateExpirationDate(currentDate))
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }

    /**
     * 校验Token
     * @param token
     * @return
     */
    public Boolean validateToken(String token) {
        try {
            //1 校验并且获取负载中的claims
            Claims claims = getAllClaimsFromToken(token);
            //2 是否过期
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        }catch (ExpiredJwtException e){
            logger.info("Token 过期");
            return false;
        }catch (Exception e){
            logger.info("Token 校验失败");
            return false;
        }
    }

    /**
     * 校验Token并且获取UserInfo
     * @param token
     * @return
     */
    public JwtUser getUserInfoFromToken(String token) {
        try {
            Claims claims = getAllClaimsFromToken(token);
            return new JwtUser(Long.valueOf(claims.get("id").toString()),(String)claims.get("username"),(String)claims.get("email"),(boolean)claims.get("enabled"));
        }catch (Exception e){
            logger.info("Token 校验失败",e);
            return null;
        }
    }

    /**
     * 更新Token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private Date calculateExpirationDate(Date currentDate) {
        return new Date(currentDate.getTime() + EXPIRATION * 1000);
    }

}
