package pro.entera.resource_service.configurations.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pro.entera.resource_service.services.JwtService;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfigProperties jwtConfigProperties;

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if (SecurityContextHolder.getContext().getAuthentication() == null) {

            Optional.ofNullable(jwtConfigProperties.getEnteraTokenHeader())
                .map(request::getHeader)
                .filter(Predicate.not(jwtService::isExpiredToken))
                .map(this::extractUser)
                .map(user -> this.createAuthToken(user, request))
                .map(this::createSecurityContext)
                .ifPresent(SecurityContextHolder::setContext);
        }

        filterChain.doFilter(request, response);
    }

    private UserDetails extractUser(String jwt) {

        return Optional.ofNullable(jwt)
            .map(jwtService::extractUserName)
            .filter(StringUtils::hasText)
            .map(User::withUsername)
            .map(User.UserBuilder::build)
            .orElse(null);
    }

    private UsernamePasswordAuthenticationToken createAuthToken(UserDetails userDetails, HttpServletRequest request) {

        final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return authToken;
    }

    private SecurityContext createSecurityContext(UsernamePasswordAuthenticationToken authToken) {

        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authToken);

        return context;
    }
}
