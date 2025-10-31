package io.github.app.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Boolean active;
	@Deprecated
	public Cliente() {
	}

	private Cliente(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.active = builder.active;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder {
		Long id;
		String name;
		Boolean active;
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder active(Boolean active) {
			this.active = active;
			return this;
		}
		public Cliente build() {
			return new Cliente(this);
		}
	}
}
