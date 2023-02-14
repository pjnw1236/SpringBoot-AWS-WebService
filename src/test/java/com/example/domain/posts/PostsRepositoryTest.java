package com.example.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void postSave() {
        String title = "테스트 게시글 제목입니다.";
        String content = "테스트 게시글 본문입니다.";
        String author = "test@gmail.com";
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts findPost = postsList.get(0);

        assertThat(findPost.getTitle()).isEqualTo(title);
        assertThat(findPost.getContent()).isEqualTo(content);
        assertThat(findPost.getAuthor()).isEqualTo(author);
    }

    @Test
    public void baseTimeEntityTest() {
        LocalDateTime now = LocalDateTime.of(2022,1,2,0,0,0);

        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println("=============================================================================================");
        System.out.println("createDate = " + posts.getCreatedDate());
        System.out.println("modifiedDate = " + posts.getModifiedDate());
        System.out.println("=============================================================================================");

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
