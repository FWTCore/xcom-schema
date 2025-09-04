package org.xcom.schema.core.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.ObjectUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * JwtUtil 工具类
 *
 * @author xcom
 * @date 2024/8/9
 */

public class JwtUtil {

    /**
     * jwt 加密解密密钥(可自行填写)
     */
    private static final String JWT_SECRET = "aHR0cHM6Ly9teS5vc2NoaW5hLm5ldC91LzM2ODE4Njg=";

    /**
     * 创建JWT
     */
    public static String generateToken(String subject, Long time) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date now = new Date(System.currentTimeMillis());

        SecretKey secretKey = generalKey();
        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
            //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
            //.setClaims(claims)
            .setSubject(subject)
            //iat: jwt的签发时间
            .setIssuedAt(now)
            //设置签名使用的签名算法和签名使用的秘钥
            .signWith(signatureAlgorithm, secretKey);
        if (ObjectUtils.isNotEmpty(time) && time >= 0) {
            //生成JWT的时间
            long nowMillis = System.currentTimeMillis();
            long expMillis = nowMillis + time;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 验证jwt
     */
    public static String parseToken(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        Claims claims;
        try {
            //得到DefaultJwtParser
            claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
        //设置需要解析的jwt
        return claims.getSubject();
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JWT_SECRET.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

}
