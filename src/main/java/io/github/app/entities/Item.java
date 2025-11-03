package io.github.app.entities;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = true, precision = 18, scale = 6)
	private BigDecimal preco;

	@Version
	@Column(updatable = false)  
	private Long version;

	private Boolean active;
	@Deprecated
	public Item() {
	}

	private Item(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.preco = builder.preco;
		this.active = builder.active;
		this.version = builder.version;
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
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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
		Item other = (Item) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", preco=" + preco
				+ ", version=" + version + ", active=" + active + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private String name;
		private BigDecimal preco;
		private Boolean active;
		private Long version;
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder version(Long version) {
			this.version = version;
			return this;
		}
		public Builder active(Boolean active) {
			this.active = active;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder preco(BigDecimal preco) {
			this.preco = preco;
			return this;
		}
		public Item build() {
			return new Item(this);
		}
	}

}
