package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetailedTopicDto;
import br.com.alura.forum.controller.dto.TopicDto;
import br.com.alura.forum.controller.form.TopicForm;
import br.com.alura.forum.controller.form.UpdateTopicForm;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @Cacheable(value = "topicList")
    public Page<TopicDto> list(@RequestParam(required = false) String courseName, @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pagination) {

        if (courseName == null) {

            Page<Topic> topics = topicRepository.findAll(pagination);
            return TopicDto.convert(topics);

        } else {

            Page<Topic> topics = topicRepository.findByCourse_Name(courseName, pagination);
            return TopicDto.convert(topics);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedTopicDto> get(@PathVariable Long id) {

        Optional<Topic> optional = topicRepository.findById(id);

        if (optional.isPresent()) {

            return ResponseEntity.ok(new DetailedTopicDto(optional.get()));

        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {

        Topic topic = form.convert(courseRepository);

        topicRepository.save(topic);

        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {

        Optional<Topic> optional = topicRepository.findById(id);

        if (optional.isPresent()) {

            Topic topic = form.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDto(topic));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Optional<Topic> optional = topicRepository.findById(id);

        if (optional.isPresent()) {

            topicRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
