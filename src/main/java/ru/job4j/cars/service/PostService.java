package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public boolean add(Post post) {
        return postRepository.add(post);
    }

    public boolean update(Post post) {
        return postRepository.update(post);
    }

    public boolean delete(int id) {
        return postRepository.delete(id);
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findPostsByCar(Car car) {
        return postRepository.findPostsByCar(car);
    }

    public List<Post> findPostsWithPhoto() {
        return postRepository.findPostsWithPhoto();
    }

    public List<Post> findPostsForLastDay() {
        return postRepository.findPostsForLastDay();
    }
}
