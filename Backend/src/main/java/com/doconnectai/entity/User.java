package com.doconnectai.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Project      : DocConnectAI
 * Author       : Panuganti Madhusudan
 * Created Date : 12 June 2026
 * Description  : Represents user data and role-based access in the system.
 *
 * Design Patterns:
 * - Entity Pattern
 * - ORM (JPA Mapping)
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "users")
public class User {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private String name;
	
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private String password;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Question> questions;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Answer> answers;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Vote> votes;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comments;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ChatMessage> messages;
}
