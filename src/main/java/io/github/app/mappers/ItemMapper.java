package io.github.app.mappers;

import org.springframework.stereotype.Component;

import io.github.app.dtos.ItemDto;
import io.github.app.entities.Item;

@Component
public class ItemMapper {

	public Item fromDto(ItemDto dto) {
		return Item.builder()
				.id(dto.id())
				.name(dto.name())
				.preco(dto.preco())
				.active(dto.active())
				
				.build();
	}
	
	public ItemDto toDto(Item item) {
		return new ItemDto(item.getId(), item.getName(), item.getPreco(),item.getActive(),item.getVersion());
	}
	
	public Item updateFromDto(ItemDto dto, Long id) {
		return Item.builder()
				.id(id)
				.name(dto.name())
				.preco(dto.preco())
				.active(dto.active())
				.build();
	}
}
