package com.purchase.utils;


import com.google.gson.Gson;
import com.purchase.service.IRedisBaseService;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * Description:Token生成工具
 * 第一部分我们称它为头部（header),第二部分我们称其为载荷（payload, 类似于飞机上承载的物品)，第三部分是签证（signature).
 * Auth: Frank
 * Date: 2017-11-02
 * Time: 下午 5:05
 */
public class TokenUtil {


    public static final String REFREH_TOKEN_AES_KEY = "defaRefreshToken";
    public static final String JWT_TYP = "JWT";
    public static final String JWT_ALG = "AES";
    public static final String JWT_EXP = "0";
    public static final String JWT_ISS = "defa";

    /**
     * 获得token
     *
     * @param data 自定义数据
     * @param <T>  自定义数据
     * @return
     * @throws Exception
     */
    public static <T> String getToken(T data) throws Exception {
        TokenPlayload<T> userTokenPlayload = new TokenPlayload<>();
        userTokenPlayload.setExpData(data);
        String jwt = createJWT(userTokenPlayload);
        return jwt;
    }

    /**
     * 生成jwt的header部分内容
     *
     * @return
     * @throws Exception
     */
    private static String tokenHeaderBase64() throws Exception {
        TokenHeader tokenHeader = new TokenHeader();
        tokenHeader.setNumber(new Date().getTime() + "");
        String headerJson = JsonUtils.objectToJson(tokenHeader);
        String headerBase64 = Base64Util.encryptBASE64(headerJson.getBytes());
        return headerBase64;
    }

    /**
     * 生成jwt的payload部分内容
     *
     * @param tokenPlayload
     * @param <T>自定义的数据块
     * @return
     * @throws Exception
     */
    private static <T> String tokenPayloadBase64(TokenPlayload<T> tokenPlayload) throws Exception {
        tokenPlayload.setIss(JWT_ISS);
        tokenPlayload.setExp(JWT_EXP);
        tokenPlayload.setIat(String.valueOf(System.currentTimeMillis()));
        String headerJson = JsonUtils.objectToJson(tokenPlayload);
        String headerBase64 = Base64Util.encryptBASE64(headerJson.getBytes());
        return headerBase64;
    }

    /**
     * 生成JWT
     *
     * @return
     */
    public static <T> String createJWT(TokenPlayload<T> tokenPlayload) throws Exception {
        StringBuilder jwtSb = new StringBuilder();
        StringBuilder headerPlayloadSb = new StringBuilder();
        String tokenHeaderBase64 = tokenHeaderBase64();
        String tokenPayloadBase64 = tokenPayloadBase64(tokenPlayload);
        jwtSb.append(tokenHeaderBase64);
        jwtSb.append(".");
        jwtSb.append(tokenPayloadBase64);
        jwtSb.append(".");
        headerPlayloadSb.append(tokenHeaderBase64);
        headerPlayloadSb.append(tokenPayloadBase64);

        String headerPlayloadSalt = SaltUtil.addSalt(headerPlayloadSb.toString());

        String key = AesUtil.initKey(SSOCommon.TOKEN_AES_KEY);

        String signature = Base64Util.encryptBASE64(AesUtil.encrypt(headerPlayloadSalt.getBytes(), key));
        jwtSb.append(signature);

        //return Base64Util.encryptBASE64(jwtSb.toString().getBytes());
        return jwtSb.toString();
    }

    /**
     * 校验token是否是服务器生成的，以防token被修改
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static <T> boolean verifyJWT(String jwt) throws Exception {
        //String jwt = new String (Base64Util.decryptBASE64(jwtBase64));
        if (!jwt.contains(".")) {
            return false;
        }
        String[] jwts = jwt.split("\\.");
        if (jwts.length < 3) {
            return false;
        }

        TokenPlayload tTokenPlayload = JsonUtils.jsonToPojo(new String(Base64Util.decryptBASE64(jwts[1])), TokenPlayload.class);
        String key = AesUtil.initKey(SSOCommon.TOKEN_AES_KEY);
        //解析出header跟playload
        StringBuilder headerPlayloadSb = new StringBuilder();
        headerPlayloadSb.append(jwts[0]);
        headerPlayloadSb.append(jwts[1]);
        //解析signature
        String headerPlayloadSalt = new String(AesUtil.decrypt(Base64Util.decryptBASE64(jwts[2]), key));
        return SaltUtil.verifyPwd(headerPlayloadSb.toString(), headerPlayloadSalt);
    }


    /**
     * 校验token是否过期
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static <T> boolean isOverdueJWT(String jwt) throws Exception {
        //String jwt = new String (Base64Util.decryptBASE64(jwtBase64));
        if (!jwt.contains(".")) {
            return false;
        }
        String[] jwts = jwt.split("\\.");
        if (jwts.length < 3) {
            return false;
        }
        TokenPlayload tTokenPlayload = JsonUtils.jsonToPojo(new String(Base64Util.decryptBASE64(jwts[1])), TokenPlayload.class);
        //校验Token是否过期,当前时间>=jwt的签发时间+EXP设置时间
        if (System.currentTimeMillis() >= (Long.parseLong(tTokenPlayload.getIat()) + SSOCommon.SSO_EXP)) {
            return false;
        }
        return true;
    }

    /**
     * 生成refreshToken
     *
     * @return
     */
    public static String createRefreshToken(String jwt) throws Exception {
        //String jwt = new String (Base64Util.decryptBASE64(jwtBase64));
        if (!jwt.contains(".")) {
            return "";
        }
        String[] jwts = jwt.split("\\.");
        if (jwts.length < 3) {
            return "";
        }
        TokenPlayload tTokenPlayload = JsonUtils.jsonToPojo(new String(Base64Util.decryptBASE64(jwts[1])), TokenPlayload.class);
        JSONObject jsonobject = JSONObject.fromObject(JsonUtils.objectToJson(tTokenPlayload));
        String newjwt = StringUilts.replaceBlank(getToken(jsonobject));
        return newjwt;
    }

    /**
     * 获取token里面用户信息
     *
     * @return
     */
    public static TokenPlayload getTokenUserData(String jwt) throws Exception {
        if (!jwt.contains(".")) {
            return null;
        }
        String[] jwts = jwt.split("\\.");
        if (jwts.length < 3) {
            return null;
        }
        TokenPlayload tTokenPlayload = JsonUtils.jsonToPojo(new String(Base64Util.decryptBASE64(jwts[1])), TokenPlayload.class);
        return tTokenPlayload;
    }

    public static UserToken getUserToken(HttpServletRequest request,IRedisBaseService redisBaiseTakes) throws Exception {
        ResponseResult result = new ResponseResult();
        String token = request.getHeader("authorization");
        UserToken userToken = null;
        if (!StringUilts.isEmptyOrNull(token)) {
            String tokenOne=token;
            token = token.substring(token.indexOf("=."), token.length()).trim();
            Gson gson = new Gson();
            userToken = gson.fromJson(gson.toJson(TokenUtil.getTokenUserData(token).getExpData()), UserToken.class);
            String tokenInfo= (String) redisBaiseTakes.get(userToken.getId()+userToken.getUserName());
            if(tokenInfo==null){
                result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
                result.setMessage("token过期");
                return null;
            }
            if(!tokenInfo.equals(tokenOne)){
                result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
                result.setMessage("token过期");
                return null;
            }
            if (userToken == null) {
                result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
                result.setMessage("需要令牌");
            } else {
                result.setStatusCode(ResponseCode.SUCCESS);
                result.setMessage("请求成功");
                result.setData(userToken);
            }
        } else {
            result.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
            result.setMessage("需要令牌");
        }
        return userToken;
    }

    public static  ResponseResult  verificationUserToken(HttpServletRequest request,IRedisBaseService redisBaiseTakes){
        ResponseResult responseResult=new ResponseResult();
        UserToken userToken =null;
        try{
            userToken = TokenUtil.getUserToken(request,redisBaiseTakes);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(userToken==null){
            responseResult.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
            responseResult.setMessage("用户token过期");
        }else {
            responseResult.setStatusCode(ResponseCode.SUCCESS);
            responseResult.setMessage("验证成功");
            responseResult.setData(userToken);
        }
        return responseResult;
    }


}
