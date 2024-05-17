package com.peter;

import com.peter.comment.Comment;
import com.peter.comment.CommentRepository;
import com.peter.post.Post;
import com.peter.post.PostRepository;
import com.peter.user.User;
import com.peter.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SocialMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			User user1 = new User(
					"user1",
					"user1@mail.com",
					passwordEncoder.encode("password"),
					""
			);
			User user2 = new User(
					"user2",
					"user2@mail.com",
					passwordEncoder.encode("password"),
					""
			);
			userRepository.saveAll(List.of(user1, user2));

			Post post1 = new Post(
					user1,
					"post1",
					new Date()
			);
			Post post2 = new Post(
					user2,
					"post2",
					new Date()
			);
			postRepository.saveAll(List.of(post1, post2));

			Comment comment1 = new Comment(
					user1,
					post1,
					"comment1",
					new Date()
			);
			Comment comment2 = new Comment(
					user2,
					post1,
					"comment2",
					new Date()
			);
			Comment comment3 = new Comment(
					user2,
					post2,
					"comment3",
					new Date()
			);
			Comment comment4 = new Comment(
					user1,
					post2,
					"comment4",
					new Date()
			);
			Comment comment5 = new Comment(
					user2,
					post2,
					"comment5",
					new Date()
			);
			commentRepository.saveAll(List.of(comment1, comment2, comment3, comment4, comment5));
		};
	}

}
