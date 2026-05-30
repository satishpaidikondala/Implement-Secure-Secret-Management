package com.example.userprofileservice.dto;

public class VaultStatusResponse {

    private boolean vaultConnected;
    private String secretPath;
    private String resolvedDbUsername;

    public VaultStatusResponse() {}

    public VaultStatusResponse(boolean vaultConnected, String secretPath, String resolvedDbUsername) {
        this.vaultConnected = vaultConnected;
        this.secretPath = secretPath;
        this.resolvedDbUsername = resolvedDbUsername;
    }

    public boolean isVaultConnected() {
        return vaultConnected;
    }

    public void setVaultConnected(boolean vaultConnected) {
        this.vaultConnected = vaultConnected;
    }

    public String getSecretPath() {
        return secretPath;
    }

    public void setSecretPath(String secretPath) {
        this.secretPath = secretPath;
    }

    public String getResolvedDbUsername() {
        return resolvedDbUsername;
    }

    public void setResolvedDbUsername(String resolvedDbUsername) {
        this.resolvedDbUsername = resolvedDbUsername;
    }
}
