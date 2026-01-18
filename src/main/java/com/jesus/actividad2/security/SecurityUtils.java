package com.jesus.actividad2.security;

import com.jesus.actividad2.exception.ForbiddenException;
import com.jesus.actividad2.model.Rol;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {
    private SecurityUtils() {
    }

    public static String currentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof AppUserDetails userDetails)) {
            throw new ForbiddenException("Usuario no autenticado");
        }
        return userDetails.getId();
    }

    public static Rol currentUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof AppUserDetails userDetails)) {
            throw new ForbiddenException("Usuario no autenticado");
        }
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        return Rol.valueOf(role.replace("ROLE_", ""));
    }
}
