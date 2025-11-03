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
				.active(dto.active() == null ? true : false)
				
				.build();
	}
	
	public ItemDto toDto(Item item) {
		return new ItemDto(item.getId(), item.getName(), item.getPreco(),item.getActive());
	}
	
	public Item updateFromDto(Item item,ItemDto dto) {
		return Item.builder()
				.id(item.getId())
				.active(item.getActive())
				.version(item.getVersion())
				.name(!dto.name().equals(item.getName()) ? dto.name() : item.getName())
				.preco(dto.preco())
				.build();
	}
}
