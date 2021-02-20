package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

  @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
  Credentials getCredentials(Long credentialid);

  @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
  List<Credentials> getCredentialsList(Long userid);


  @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialid")
  int insert(Credentials credentials);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
  int delete(Long credentialid);

  @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} WHERE credentialid = #{credentialid}")
  int update(Long credentialid, String url, String username, String password);
}
