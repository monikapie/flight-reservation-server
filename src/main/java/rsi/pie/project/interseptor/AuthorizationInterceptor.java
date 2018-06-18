package rsi.pie.project.interseptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userPass = request.getHeader("authorization").substring(6);
        byte[] decodedBytes = Base64.getDecoder().decode(userPass);
        userPass = new String(decodedBytes);
        System.out.println("Decoded username and password " + userPass);
        if(userPass.equals(":")){
            System.out.println("Invalid User Id or Password. Please try again.");
            response.addHeader("LoginStatus", "Invalid User Id or Password. Please try again.");
        }
        String[] username = userPass.split(":");
        if(!"admin".equals(username[0])) {
            response.addHeader("LoginStatus", "Username or password are not correct.");
        }

        return true;
    }


}