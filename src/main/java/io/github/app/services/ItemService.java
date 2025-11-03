package io.github.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.app.dtos.ItemDto;
import io.github.app.mappers.ItemMapper;
import io.github.app.repositories.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ItemService {

	private final ItemRepository itemRepository;
	private final ItemMapper itemMapper;
	public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
	}

	public List<ItemDto> findAllActive(){
		return itemRepository.findAllByActiveTrue().parallelStream().map(m->itemMapper.toDto(m)).toList();
	}
	public ItemDto findById(Long id) {
		var item = itemRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Item não encontrado"));

		return itemMapper.toDto(item);
	}
	public void createItem(ItemDto dto) {
		var item = itemMapper.fromDto(dto);
		item.setActive(true);
		itemRepository.save(item);
	}

	@Transactional
	public void itemDisable(Long id) {
		var item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("item não encontrado"));
		if(item.getActive()) {
			item.setActive(false);
		}
	}

	
	public void itemUpdate(Long id, @Valid ItemDto dto) {
		var item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("item não encontrado"));
		item = itemMapper.updateFromDto(item,dto);
		itemRepository.save(item);
	}
}
