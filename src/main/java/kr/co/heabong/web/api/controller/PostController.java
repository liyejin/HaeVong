package kr.co.heabong.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.Post;
import kr.co.heabong.web.service.PostService;

@RestController("apiPostController")

@RequestMapping("api/posts")
public class PostController {

	@Autowired
	private PostService service;

	@GetMapping
	public List<Post> list() {
		List<Post> list = service.getList();
		return list;
	}

	@PostMapping
	public ResponseEntity<Object> add(@RequestBody Post post) {
		Post newOne = service.write(post);
		if (newOne == null) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(newOne, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Object> update(@RequestBody Post post) {
		Post updatedOne = service.update(post);
		if (updatedOne == null) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(updatedOne, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		int result = service.delete(id);
		if (result == 0)
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
