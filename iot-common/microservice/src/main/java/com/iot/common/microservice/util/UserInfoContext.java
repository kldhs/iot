package com.iot.common.microservice.util;

import com.iot.common.data.model.dto.security.UserInfoDto;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class UserInfoContext {

    private UserInfoContext() {
    }

    private static final ThreadLocal<UserInfoDto> userInfoThreadLocal = new ThreadLocal<>();

    public static synchronized void initUserInfo(UserInfoDto userInfoDTO) {
        userInfoThreadLocal.set(userInfoDTO);
    }

    public static UserInfoDto getUserInfo() {
        return userInfoThreadLocal.get();
    }

    public static void setUserInfo(UserInfoDto userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    public static Long getUserId() {
        if (Objects.nonNull(getUserInfo())) {
            return getUserInfo().getUserId();
        }
        return null;
    }

    public static String getUsername() {
        if (Objects.nonNull(getUserInfo())) {
            return getUserInfo().getUsername();
        }
        return null;
    }

    public static String getCompanyCode() {
        if (Objects.nonNull(getUserInfo())) {
            return getUserInfo().getCompanyCode();
        }
        return null;
    }

    public static Boolean isAdmin() {
        if (Objects.nonNull(getUserInfo())) {
            return getUserInfo().getAdmin();
        }
        return null;
    }


    public static Integer roleLevel() {
        if (Objects.nonNull(getUserInfo())) {
            return getUserInfo().getRoleLevel();
        }
        return null;
    }

    public static HttpServletRequest getRequest() {
        if (Objects.nonNull(getUserInfo())) {
            return getUserInfo().getRequest();
        }
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static String getToken() {
        if (Objects.nonNull(getUserInfo())) {
            return getUserInfo().getToken();
        }
        return null;
    }

    public static Long companyId() {
        if (Objects.nonNull(getUserInfo())) {
            return getUserInfo().getCompanyId();
        }
        return null;
    }


    public static void remove() {
        userInfoThreadLocal.remove();
    }
}
