package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

  @Select("SELECT * FROM CREDENTIALS WHERE credentialsid = #{credentialsid}")
  Credentials getCredentials(Long credentialsid);

  @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
  List<Credentials> getCredentialsList(Long userid);


  @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialsid")
  int insert(Credentials credentials);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialsid = #{credentialsid}")
  int delete(Long credentialsId);
}
