package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.util.List;

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
    String key = credentials.getKey();
    String encrypted = encryptionService.encryptValue(plain, key);

    credentials.setPassword(encrypted);

    int r = credentialsMapper.insert(credentials);

    System.out.println("inserted credentials " + credentials.toString());

    return r;
  }

  public List<Credentials> getCredentials(Long userid) {
    List<Credentials> credentialsList = credentialsMapper.getCredentialsList(userid);

    return credentialsList;
  }


}
