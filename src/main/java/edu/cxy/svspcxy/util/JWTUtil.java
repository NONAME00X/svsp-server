package edu.cxy.svspcxy.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.util.Date;

public class JWTUtil {
    public static final String SECRET_KEY = "成都信息工程大学"; //秘钥
    public static final long TOKEN_EXPIRE_TIME = 60 * 60 * 1000; //token过期时间
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 10 * 60 * 1000; //refreshToken过期时间
    private static final String ISSUER = "软件工程"; //签发人

    /**
     * 生成签名
     */
    public static String generateToken(int uid, String account){
        Date now = new Date();
        //创建签名算法对象
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法

        String token = JWT.create()
                .withIssuer(ISSUER) //签发人
                .withIssuedAt(now)  //签发时间
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME)) //过期时间
                .withClaim("uid", uid) //保存身份标识
                .withClaim("account", account)
                .sign(algorithm);
        return token;
    }

    /**
     * 验证token
     */
    public static TokenEnum verify(String token){
        try {
            //签名算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token);
            return TokenEnum.TOKEN_SUCCESS;
        } catch (TokenExpiredException ex){
            return TokenEnum.TOKEN_EXPIRE;
            //ex.printStackTrace();
        } catch (Exception e) {
            return TokenEnum.TOKEN_BAD;
        }
    }

    /**
     * 从token获取uid
     */
    public static int getuid(String token){
        try{
            return JWT.decode(token).getClaim("uid").asInt();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public static String getAccount(String token){
        try{
            return JWT.decode(token).getClaim("account").asString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}