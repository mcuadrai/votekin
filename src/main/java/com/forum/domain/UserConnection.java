package com.forum.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/*@Entity
@Table(name="users", indexes = {
	@Index(columnList = "email", unique=true)
})*/

/*userId varchar(255) not null,
providerId varchar(255) not null,
providerUserId varchar(255),
rank int not null,
displayName varchar(255),
profileUrl varchar(512),
imageUrl varchar(512),
accessToken varchar(512) not null,
secret varchar(512),
refreshToken varchar(512),
expireTime bigint,
primary key (userId, providerId, providerUserId)
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
*/

@Entity
public class UserConnection {
	
	@Id
	@Column(name="userId", length=255)
	private String userId;
	
	@Column(name="providerId", length=255)
	private String providerId;
	
	@Column(name="providerUserId", length=255)
	private String providerUserId;
	
	@Column(name="rank")
	private Integer rank;
	
	@Column(name="displayName", length=255)
	private String displayName;
	
	@Column(name="profileUrl", length=512)
	private String profileUrl;
	
	@Column(name="imageUrl", length=512)
	private String imageUrl;
	
	@Column(name="accessToken", length=512)
	private String accessToken;
	
	@Column(name="secret", length=512)
	private String secret;
	
	@Column(name="refreshToken", length=512)
	private String refreshToken;
	
	@Column(name="expireTime")
	private Long expireTime;
    
}
