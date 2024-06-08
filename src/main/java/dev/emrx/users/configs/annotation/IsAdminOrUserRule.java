package dev.emrx.users.configs.annotation;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@PostAuthorize("hasRole('ROLE_ADMIN')")
public @interface IsAdminOrUserRule {
}
