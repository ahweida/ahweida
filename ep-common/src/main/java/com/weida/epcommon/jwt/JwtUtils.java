package com.weida.epcommon.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.*;
import java.util.*;

/**
 * JWT 工具类
 *
 * @author apelx
 * @since 2020-11-22
 */
public class JwtUtils {

    public static final String AUTH_HEADER_KEY = "Authorization";

    private static final String JWT_PAYLOAD_USER_KEY = "user";
    //有效期默认为 2hour
    public static final Long EXPIRATION_TIME = 1000L*60*60*10;

    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxyMq23jDsGausQgNGD9RebhL+rSCVtyXXrH7Xt8giexdOA0QSWVC7Yhq3FA7OiaqeHzmVinkant2dpKJxOqkXtXSoEg27fZWyqw1s+6su/FHPN7QbcDoUG53s00d7VQkKP3zsVTBBFSXa7I82YzUx6tLCFa9hrlUnDqag9iiY2mtb5+oqrXufWPb1Fy3WZkgTg6kdzi8tH7eBu/Vw2yAt1i2q39Jwt/1R8XvYkqO/x33lWw+21NoqC3c8BMZQt3iJJIQR9+MKx1+g9EExbU1vJFx2h5bwTtJuzLyOoztEjd7eNl5eT/M0VrjG3Q+QtmG6U18MAhTMpd7sO24rOX2HwIDAQAB";

    public static void main(String[] args) throws Exception {
//        String publicKey =  JwtUtils.class.getClassLoader().getResource("rsa_key_pub").getPath();
//        String privateKey = JwtUtils.class.getClassLoader().getResource("rsa_key_pri").getPath();
//
//
//        Map<String, Object> dbUser = Maps.newHashMap();
//        dbUser.put("id", 123);
//        String token = JwtUtils.generateToken(dbUser, RsaUtils.getPrivateKey(privateKey));
//        System.out.println(token);
//        JwtUtils.parserToken(token);
//        Payload<Map> payload = JwtUtils.getInfoFromToken(token, Map.class);;
//        Map userInfo = payload.getUserInfo();
//        System.out.println(userInfo);
//        Map map =getKeys();
//        System.out.println(map.get("privateKey"));
//        System.out.println(map.get("publicKey"));

//        PrivateKey aPrivate = RsaUtils.getPrivate(privateKey);
//        PublicKey aPublic = RsaUtils.getPublic(publicKey);
//        Map<String, Object> dbUser = Maps.newHashMap();
//        dbUser.put("id", 123);
//        String token = JwtUtils.generateToken(dbUser, aPrivate);
//        System.out.println(token);
//        JwtUtils.parserToken(token);
//        Payload<Map> payload = JwtUtils.getInfoFromToken(token,aPublic, Map.class);;
//        Map userInfo = payload.getUserInfo();
//        System.out.println(userInfo);

    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/20 14:15
     * @Desc: 获取密钥对
     */
    public static HashMap<String, String> getKeys() {
        HashMap<String, String> map = new HashMap<String, String>();
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(2048, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PublicKey aPublic = keyPair.getPublic();
        // 得到公钥字符串
        String publicKey = base64ToStr(keyPair.getPublic().getEncoded());
        // 得到私钥字符串
        String privateKey = base64ToStr(keyPair.getPrivate().getEncoded());
        map.put("publicKey", publicKey);
        map.put("privateKey", privateKey);
        return map;
    }

    public static String base64ToStr(byte[] b) {
        return javax.xml.bind.DatatypeConverter.printBase64Binary(b);
    }

    public static byte[] strToBase64(String str) {
        return javax.xml.bind.DatatypeConverter.parseBase64Binary(str);
    }

    /**
     * 私钥加密token
     *
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥
     * @return JWT
     */
    public static String generateToken(Object userInfo, PrivateKey privateKey) {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                .setId(createJTI())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/11 16:22
     * @Desc: 公钥解析token
     */
    public static Jws<Claims> parserToken(String token) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublic(publicKey)).parseClaimsJws(token);
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/20 14:17
     * @Desc: 带公钥参数解析token
     */
    public static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/11 16:17
     * @Desc: 获取token中的用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, Class<T> userType) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, RsaUtils.getPublic(publicKey));
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setUserInfo(JsonUtils.toBean(body.get(JWT_PAYLOAD_USER_KEY).toString(), userType));
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey, Class<T> userType) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setUserInfo(JsonUtils.toBean(body.get(JWT_PAYLOAD_USER_KEY).toString(), userType));
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * @Auther: zhaofei
     * @Date: 2021/1/11 16:26
     * @Desc: 获取token中的载荷信息
     */
    public static <T> Payload<T> getInfoFromToken(String token) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, RsaUtils.getPublic(publicKey));
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * 获取token中的载荷信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setExpiration(body.getExpiration());
        return claims;
    }
}