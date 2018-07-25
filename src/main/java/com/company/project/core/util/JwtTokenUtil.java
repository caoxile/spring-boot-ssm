package com.company.project.core.util;

import com.company.project.auth.model.User;
import com.company.project.configuration.WebMvcConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    private static final String SECRET = "my_secret"; //密钥
    private static final Long EXPIRATION = 60*60L; //Token有效时间(1小时);


    /**
     * 方法中默认没有设置Header,设置了负载的用户信息claims,签发时间,过期时间
     * @param user
     * @return
     */
    public static String generateToken(User user){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",user.getUserId());
        claims.put("loginName",user.getLoginName());
        claims.put("userName",user.getUserName());
        claims.put("email",user.getEmail());
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
    public static Boolean validateToken(String token) {
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
    public static User getUserInfoFromToken(String token) {
        try {
            Claims claims = getAllClaimsFromToken(token);
            User user = new User();
            user.setUserId(Integer.valueOf(claims.get("userId").toString()));
            user.setUserName((String)claims.get("userName"));
            user.setLoginName((String)claims.get("loginName"));
            user.setEmail((String)claims.get("email"));
            return user;
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
    public static String refreshToken(String token) {
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

    private static Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private static Date calculateExpirationDate(Date currentDate) {
        return new Date(currentDate.getTime() + EXPIRATION * 1000);
    }

}
