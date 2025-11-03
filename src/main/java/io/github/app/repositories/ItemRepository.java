package io.github.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.app.entities.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {

	Optional<Item>  findByName(String name);
	List<Item> findAllByActiveTrue();
}
