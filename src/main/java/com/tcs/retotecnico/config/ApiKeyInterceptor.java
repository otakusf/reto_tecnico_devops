package com.tcs.retotecnico.config;

import com.tcs.retotecnico.jwt.OneTimeTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Autowired
    private OneTimeTokenService oneTimeTokenService;


    private static final String EXPECTED_API_KEY = "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c";
    private static final String HEADER_API_KEY = "X-Parse-REST-API-Key";
    private static final String HEADER_JWT = "X-JWT-KWY";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // Si no es un controller (ej. recurso estático), deja pasar
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // Leer cabeceras
        String apiKey = request.getHeader(HEADER_API_KEY);
        String jwt = request.getHeader(HEADER_JWT); // por si luego lo necesitas

        // Validar API KEY
        if (apiKey == null || !EXPECTED_API_KEY.equals(apiKey)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Invalid API Key\"}");
            return false; // corta la cadena, no llega al controller
        }

        // Si quieres validar también JWT, aquí lo haces
         if (jwt == null || jwt.isBlank()) {
             response.setStatus(HttpStatus.FORBIDDEN.value());
             response.setContentType("application/json");
             response.getWriter().write("{\"error\":\"Missing or invalid JWT\"}");
             return false;
         }

        boolean ok = oneTimeTokenService.consumeToken(jwt);

        if (!ok) {
            response.setStatus(HttpStatus.CONFLICT.value()); // o 401/403 según tu caso
            response.getWriter().write("{\"error\":\"Token invalid, expired or already used\"}");
            return false;
        }

        return true; // OK, continúa al controller
    }
}
