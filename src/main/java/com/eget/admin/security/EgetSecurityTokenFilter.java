package com.eget.admin.security;

import com.eget.admin.pojo.User;
import com.eget.admin.security.model.AuthUserDetail;
import com.eget.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author geforce
 * @date 2017/12/11
 */
public class EgetSecurityTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenFactory tokenFactory;

    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isNotBlank(token) && tokenFactory.getEgetTokenUtil().isSupportToken(token)) {
            if (tokenFactory.getEgetTokenUtil().isTokenExpired(token)){
                throw new EgetAuthException("Token过期了");
            }
            String username = tokenFactory.getEgetTokenUtil().getSafeUserName(token);
            if (StringUtils.isNotBlank(username)) {
                tokenFactory.getEgetTokenUtil();

                User user = userService.getUser(username);
                if (user == null){
                    throw new EgetAuthException("Token错误");
                }

                UserDetails userDetails = new AuthUserDetail(user.getUsername(),user.getPassword());
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//        authenticationToken.setDetails();
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }else {
                throw new EgetAuthException("Token错误");
            }



        }





        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
}
