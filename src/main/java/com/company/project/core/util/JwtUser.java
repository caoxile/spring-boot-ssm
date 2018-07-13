package com.company.project.core.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser {

    private final Long id;
    private final String username;
    private final String email;
    private final boolean enabled;

    public JwtUser(
          Long id,
          String username,
          String email,
          boolean enabled
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
