package net.rokyinfo.insurance.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.rokyinfo.insurance.exception.RkAuthorizationException;
import net.rokyinfo.insurance.util.JacksonUtil;
import net.rokyinfo.insurance.util.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 *
 * @author zhaoxinguo on 2017/9/13.
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (RkAuthorizationException e) {

            R errorResponse = new R(e);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            String json = JacksonUtil.toJSon(errorResponse);
            System.out.println(json);
            if (!StringUtils.isEmpty(json)) {
                response.getWriter().write(json);
            }
            return;
        }
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
            Claims claims = Jwts.parser()
                    .setSigningKey("InsuranceJwtSecret")
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            // 拿用户名
            String user = claims.getSubject();

            // 得到 权限（角色）
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }

            throw new RkAuthorizationException("授权信息无效", HttpStatus.UNAUTHORIZED.value());
        }

        throw new RkAuthorizationException("无授权信息", HttpStatus.UNAUTHORIZED.value());
    }

}
