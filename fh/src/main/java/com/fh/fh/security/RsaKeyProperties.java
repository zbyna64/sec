package com.fh.fh.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Objects;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {

  private RSAPublicKey publicKey;
  private RSAPrivateKey privateKey;

  public RsaKeyProperties() {
  }

  RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
    this.publicKey = publicKey;
    this.privateKey = privateKey;
  }

  public RSAPublicKey publicKey() {
    return publicKey;
  }

  public RSAPrivateKey privateKey() {
    return privateKey;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    RsaKeyProperties that = (RsaKeyProperties) obj;
    return Objects.equals(this.publicKey, that.publicKey) &&
        Objects.equals(this.privateKey, that.privateKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(publicKey, privateKey);
  }

  @Override
  public String toString() {
    return "RsaKeyProperties[" +
        "publicKey=" + publicKey + ", " +
        "privateKey=" + privateKey + ']';
  }

  public RSAPublicKey getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(RSAPublicKey publicKey) {
    this.publicKey = publicKey;
  }

  public RSAPrivateKey getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(RSAPrivateKey privateKey) {
    this.privateKey = privateKey;
  }
}
