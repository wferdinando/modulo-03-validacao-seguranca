package com.devsuperior.dscatalog.dto;

import java.io.Serializable;

import com.devsuperior.dscatalog.entities.Role;

public class RoletDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String authority;

    public RoletDTO() {
    }

    public RoletDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoletDTO(Role role) {
        id = role.getId();
        authority = role.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
