package com.ireslab.electraapp.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.electraapp.entity.OAuthRefreshToken;

/**
 * @author Nitin
 *
 */
public interface OAuthRefreshTokenRepository extends CrudRepository<OAuthRefreshToken, Serializable> {

	public OAuthRefreshToken findByTokenId(String tokenId);

}
