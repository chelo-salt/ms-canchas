package cl.municipalidad.ms_canchas.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String header = request.getHeader("Authorization");
        
        // --- DEBUG: Ver si llega algo ---
        System.out.println("DEBUG: Filtro ejecutado para la ruta: " + request.getRequestURI());
        System.out.println("DEBUG: Header Authorization: " + header);

        if (header == null || !header.startsWith("Bearer ")) {
            System.out.println("DEBUG: No hay token o no empieza con Bearer.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", "");
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);

            String username = decodedJWT.getSubject();
            System.out.println("DEBUG: Token verificado con éxito. Usuario: " + username);

            if (username != null) {
                UsernamePasswordAuthenticationToken auth = 
                    new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("DEBUG: Autenticación establecida en el contexto.");
            }
        } catch (Exception e) {
            System.out.println("DEBUG: Error al validar token: " + e.getMessage());
            // Si el secreto es incorrecto, aquí dirá "The Token's Signature resulted invalid"
        }

        filterChain.doFilter(request, response);
    }
}