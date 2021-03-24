package com.weida.epuser.controller;

import com.google.common.collect.Maps;
import com.weida.epcommon.jwt.JwtUtils;
import com.weida.epcommon.jwt.RsaUtils;
import com.weida.epcommon.util.AesUtil;
import com.weida.epuser.dto.CrmRelationUser;
import com.weida.epuser.dto.User;
import com.weida.epuser.service.CrmRelationUserService;
import com.weida.epuser.service.UserService;
import com.weida.epcommon.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/loginController")
@Slf4j
public class LoginController {

    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxyMq23jDsGausQgNGD9RebhL+rSCVtyXXrH7Xt8giexdOA0QSWVC7Yhq3FA7OiaqeHzmVinkant2dpKJxOqkXtXSoEg27fZWyqw1s+6su/FHPN7QbcDoUG53s00d7VQkKP3zsVTBBFSXa7I82YzUx6tLCFa9hrlUnDqag9iiY2mtb5+oqrXufWPb1Fy3WZkgTg6kdzi8tH7eBu/Vw2yAt1i2q39Jwt/1R8XvYkqO/x33lWw+21NoqC3c8BMZQt3iJJIQR9+MKx1+g9EExbU1vJFx2h5bwTtJuzLyOoztEjd7eNl5eT/M0VrjG3Q+QtmG6U18MAhTMpd7sO24rOX2HwIDAQAB";
    public static String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDHIyrbeMOwZq6xCA0YP1F5uEv6tIJW3Jdesfte3yCJ7F04DRBJZULtiGrcUDs6Jqp4fOZWKeRqe3Z2konE6qRe1dKgSDbt9lbKrDWz7qy78Uc83tBtwOhQbnezTR3tVCQo/fOxVMEEVJdrsjzZjNTHq0sIVr2GuVScOpqD2KJjaa1vn6iqte59Y9vUXLdZmSBODqR3OLy0ft4G79XDbIC3WLarf0nC3/VHxe9iSo7/HfeVbD7bU2ioLdzwExlC3eIkkhBH34wrHX6D0QTFtTW8kXHaHlvBO0m7MvI6jO0SN3t42Xl5P8zRWuMbdD5C2YbpTXwwCFMyl3uw7bis5fYfAgMBAAECggEBAL9lKJLqnkLUXkCeeV99tARmQviCN+feeso0DGLh17dYazwtKiMw5UPB4ts/bb11XlER0pysBPLnGTPbofiWgUb5Aki1P741DrQbEj6PPowg2ZRCn1dtmpt7oSa/Du2E+oA/nNIqoJ1V5HdJRzzdI8GI/EPWgrCTPiQuG0Zvl5wtStP1TPNqX1wlfX7TDSLJKRE14NkUeroy3cgubZal9BWw07liEQ1trnLOFqWSZaSJjm+6C980jfra0wVkxD2UtrOEiCh70xL9/XP59qTH3QVIem4kf6AkgKdhrGBA2F184InCGBSGEco0jSqB7UZ8FpABDuqZVYgJYecRIzYJ1XECgYEA4lhwY3eMPryDjMC5jRYJzYH2f59IonwRdHJZ+bgvYGUiT9uIQOD9lsjlELrzuDqzpSj+tbZElYuKWWrr+U5Wp6f3pJCUPl50a7SujlnkQ1K+cbw7owkpKz9EAeEOuzqWjQgzBsTqA9mrN8XAtD0NKiCKEYbxB7l3GcqAUFFYPAkCgYEA4TotSY/YfBVHMQlzQ7sZWS9F84hgMaK2RHMdp1GWgNGlWkYAn9t41qwNyyi5D8rWAMr7zw1UUVP7VFeMnhgq2ywQ59+Ly+Rd3iAAnhGILqkW8WCev6yhKNP2bypIQ6iNUDrzjgR10VrcBkNObkp6Tlb1iFrez1z1NnS+TOYv+ucCgYEAnPwVxwcNQB3jZ2Xry4yzc9NlsZI6AGfoe0ksWmSCh2rdZ2GtmMHzX/cp2Haxvt8H5c8PCdzGqKnFZMshqyHIPDPlMEAOcUt1l5YTUyvJg2AKMjwJaMRbx2DzvBHp87Vo4ZwEN/evDsiTVfVT+C43yTYUTJ+44FCj6EFoyUwB1HECgYA0/VYIV6KMVwk/3URULCHjnL6tdGmFZA3T/u18HzL0rd5kZdHhymYcw7AfGz6nG4bL+vXRmgY7eDHKfyqjS3ZRyIVVuH+QgfMfvpHGnsNWjwvJ0HypBfmIvI3fS6hi2rYcqTICOMRrdCAODSnXwwgRhF4SLab8TJeYr1DSkNm7AwKBgQDaMrck53gk1Ny5zuVkA+2iVEld0Ia02eMiQi7IsX6ZddQO3GqBn4k7n/JOLQbSUSy2t3Rv2wZgP+5rsqctAdIc3xWydEbkiwE8vw5cxP+9DFAzG6IFyMv+grMn4uaH7OjOk7Z1qitC+KRnG/3sfp+MEwWHjL+D21tcIE7zd1cUtg==";
    public static String aesKey = "ahhxhbkjtltxywxm";

    @Autowired
    private UserService userService;
    @Autowired
    private CrmRelationUserService crmRelationUserService;


    /**
     * 登录
     * @param
     * @return
     */
    @PostMapping(value = "/front/login")
    public CommonResult frontLogin(String account, String password, HttpServletResponse response){
        //...参数合法性验证
        User user = new User();
        user.setAccount(account).setPassword(password);
        if (user.getAccount() == null || "".equals(user.getAccount().trim())
                || user.getPassword() == null ||  "".equals(user.getPassword().trim())){
            return CommonResult.fail("账号或密码为空");
        }

        //从数据库获取用户信息
        User dbUser = userService.getUser(user);

        //....用户、密码验证
        if(dbUser == null){
            return CommonResult.fail("账号或密码不正确");
        }

        // todo  应该是判断角色是否是系统管理员，非管理员无法登录，后续权限管理完成后需添加相应功能
        if(dbUser.getId() != 1){
            return CommonResult.fail("账号无权登录");
        }

        String token;
        try {
             token = JwtUtils.generateToken(dbUser, RsaUtils.getPrivate(privateKey));
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("生成token失败，请联系管理员");
        }

        Map result = Maps.newHashMap();
        result.put(JwtUtils.AUTH_HEADER_KEY, token);
        result.put("userInfo", dbUser);

        return CommonResult.success(result, "登录成功");
    }


    /**
     * 登录
     * @param
     * @return
     */
    @PostMapping(value = "/screen/login")
    public CommonResult screenLogin(String account, String password, HttpServletResponse response){
        //...参数合法性验证
        User user = new User();
        user.setAccount(account).setPassword(password);
        if (user.getAccount() == null || "".equals(user.getAccount().trim())
                || user.getPassword() == null ||  "".equals(user.getPassword().trim())){
            return CommonResult.fail("账号或密码为空");
        }

        //从数据库获取用户信息
        User dbUser = userService.getUser(user);

        //....用户、密码验证
        if(dbUser == null){
            return CommonResult.fail("账号或密码不正确");
        }

        String token;
        try {
            token = JwtUtils.generateToken(dbUser, RsaUtils.getPrivate(privateKey));
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("生成token失败，请联系管理员");
        }

        Map result = Maps.newHashMap();
        result.put(JwtUtils.AUTH_HEADER_KEY, token);
        result.put("userInfo", dbUser);

        return CommonResult.success(result, "登录成功");
    }

    /**
     * 登录
     * @param
     * @return
     */
    @PostMapping(value = "/ssoLogin")
    public CommonResult ssoLogin(String crmAccount){
        String decryptAccount = AesUtil.decrypt(crmAccount, aesKey);

        //...参数合法性验证
        if (decryptAccount == null || "".equals(decryptAccount.trim())){
            return CommonResult.fail("账号有误，请联系系统管理员");
        }

        //从数据库获取用户信息
        User dbUser = userService.getUserByCrmAccount(decryptAccount);

        //用户验证
        if (dbUser == null){
            return CommonResult.fail("账号未绑定，请联系系统管理员");
        }

        String token;
        try {
            token = JwtUtils.generateToken(dbUser, RsaUtils.getPrivate(privateKey));
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail("生成token失败，请联系管理员");
        }

        Map result = Maps.newHashMap();
        result.put(JwtUtils.AUTH_HEADER_KEY, token);
        result.put("userInfo", dbUser);

        return CommonResult.success(result, "登录成功");
    }


    @PostMapping(value = "/autoLogin")
    public CommonResult autoLogin(String crmAccount, HttpServletResponse response) throws IOException {
        log.info("http://www.baidu.com");
        //response.sendRedirect("http://www.baidu.com");
        return CommonResult.success("登录成功");
    }

    @PostMapping(value = "/autoLogin2")
    public void autoLogin2(String crmAccount, HttpServletResponse response) throws IOException {
        log.info("http://www.baidu.com");
        response.sendRedirect("http://localhost:8002");
    }

}
