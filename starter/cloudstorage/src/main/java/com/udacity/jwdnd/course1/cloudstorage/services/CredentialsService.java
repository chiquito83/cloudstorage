package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialsService {

  private CredentialsMapper credentialsMapper;
  private EncryptionService encryptionService;

  public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
    this.credentialsMapper = credentialsMapper;
    this.encryptionService = encryptionService;
  }

  public int createCredentials(Credentials credentials) {

    String plain = credentials.getPassword();
    SecureRandom random = new SecureRandom();

    byte[] key = new byte[16];
    random.nextBytes(key);

    String encodedKey = Base64.getEncoder().encodeToString(key);

    String encrypted = encryptionService.encryptValue(plain, encodedKey);

    credentials.setPassword(encrypted);
    credentials.setKey(encodedKey);

    int r = credentialsMapper.insert(credentials);

    System.out.println("inserted credentials " + credentials.toString());

    return r;
  }

  public Credentials getById(Long credentialsId) {
    return credentialsMapper.getCredentials(credentialsId);
  }

  public int delete(Long credentialsId) {
    return credentialsMapper.delete(credentialsId);
  }

  public List<Credentials> getCredentials(Long userid) {
    List<Credentials> credentialsList = credentialsMapper.getCredentialsList(userid);


    return credentialsList.stream()
            .map(c -> decrypt(c)).collect(Collectors.toList());
  }

  private Credentials decrypt(Credentials credentials) {
    String key = credentials.getKey();
    String encrypted = credentials.getPassword();

    String password = encryptionService.decryptValue(encrypted, key);

    credentials.setPassword(password);

    return credentials;
  }


}
