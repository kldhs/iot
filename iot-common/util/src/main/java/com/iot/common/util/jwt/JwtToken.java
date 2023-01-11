package com.iot.common.util.jwt;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWTValidator;
import com.iot.common.data.exception.TokenInvalidException;
import com.iot.common.data.model.bo.gateway.UserInfo;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;

public class JwtToken {

    //token valid time four hours
    static final Long TOKEN_MAX_EX = 4 * 60 * 60 * 1000L;

    public static final String CLAIM_USERID = "userId";
    public static final String CLAIM_COMPANY_ID = "companyId";
    public static final String CLAIM_ROLE_ID = "roleId";
    public static final String CLAIM_ADMIN = "admin";

    private JwtToken() {
    }

    /**
     * 1. 获取密钥
     *
     * @return
     */
    static RSAKey getRSASSASigner() {
        return RsaKeyLoad.getRsaKey();
    }

    /**
     * 2. 建立payload 载体
     */
    static JWTClaimsSet setClaims(UserInfo userInfo, String issuer) {
        return new JWTClaimsSet.Builder()
                .issuer(issuer)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + TOKEN_MAX_EX))
                .claim(CLAIM_USERID, userInfo.userId())
                .claim(CLAIM_COMPANY_ID, userInfo.companyId())
                .claim(CLAIM_ROLE_ID, userInfo.roleId())
                .claim(CLAIM_ADMIN, userInfo.admin())
                .build();
    }

    /**
     * 3. 获取JWSHeader
     */
    static JWSHeader jwsHeader() {
        return new JWSHeader.Builder(JWSAlgorithm.RS256).build();
    }

    /**
     * 获取token
     */
    public static String getToken(UserInfo userInfo, String issuer) {
        try {
            //建立签名
            SignedJWT signedJWT = new SignedJWT(jwsHeader(), setClaims(userInfo, issuer));
            var jwsSigner = new RSASSASigner(getRSASSASigner());
            signedJWT.sign(jwsSigner);
            //获取token
            String token = signedJWT.serialize();
            return token;
        } catch (JOSEException e) {
            throw new IllegalArgumentException("token generate error", e);
        }
    }

    /**
     * 验证token
     */
    public static boolean verify(String jwtToken) {
        try {
            var signedJwt = SignedJWT.parse(jwtToken);
            JWSVerifier verifier = new RSASSAVerifier(getRSASSASigner().toPublicJWK());
            return signedJwt.verify(verifier) && validateDate(jwtToken);
        } catch (Exception e) {
            throw new IllegalArgumentException("token verify error");
        }
    }

    private static Boolean validateDate(String jwtToken) {
        try {
            JWTValidator.of(jwtToken).validateDate(DateUtil.date());
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 获取信息
     */
    public static UserInfo loadUserInfo(String jwtToken) {
        try {
            var claims = loadClaims(jwtToken);
            return new UserInfo(
                    claims.getLongClaim(CLAIM_USERID),
                    claims.getLongClaim(CLAIM_COMPANY_ID),
                    claims.getLongClaim(CLAIM_ROLE_ID),
                    claims.getBooleanClaim(CLAIM_ADMIN)
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("token claims parse error", e);
        }
    }

    public static JWTClaimsSet loadClaims(String jwtToken) {
        try {
            var signedJwt = SignedJWT.parse(jwtToken);
            JWSVerifier verifier = new RSASSAVerifier(RsaKeyLoad.getRsaKey().toPublicJWK());
            if (!signedJwt.verify(verifier)) {
                throw new TokenInvalidException("token invalid");
            }
            return signedJwt.getJWTClaimsSet();
        } catch (Exception e) {
            throw new IllegalArgumentException("token verify error", e);
        }
    }
}
