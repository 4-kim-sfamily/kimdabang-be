package com.kimdabang.kdbserver.common.entity;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class OAuthAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;

    public OAuthAuthenticationToken(String uuid) {
        super(null);
        this.principal = uuid;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null; // OAuth에서는 자격 증명이 필요하지 않음
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
