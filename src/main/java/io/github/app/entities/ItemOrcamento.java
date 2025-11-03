package io.github.app.entities;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "items_orcamentos")
public class ItemOrcamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_item", nullable = false)
	private Item item;


	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal preco;

	@Column(nullable = false)
	private Long quantidade;

	@Column(name = "id_orcamento", insertable = false,updatable = false)
	private Long idOrcamento;
	
	@Deprecated
	protected ItemOrcamento() {
	}

	private ItemOrcamento(Builder builder) {
		this.id = builder.id;
		this.item = builder.item;
		this.preco = builder.preco != null ? builder.preco : BigDecimal.ZERO;
		this.quantidade = builder.quantidade;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	

	

	@Override
	public String toString() {
		return "ItemOrcamento [id=" + id + ", item=" + item + ", preco=" + preco
				+ ", quantidade=" + quantidade + ", idOrcamento=" + idOrcamento
				+ "]";
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
		ItemOrcamento other = (ItemOrcamento) obj;
		return Objects.equals(id, other.id);
	}

	public static Builder builder() {
		return new Builder();
	}
	public static class Builder {
        private Long id;
        private Item item;
        private BigDecimal preco;
        private Long quantidade;

        private Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder item(Item item) {
            this.item = item;
            return this;
        }

        public Builder preco(BigDecimal preco) {
            this.preco = preco;
            return this;
        }

        public Builder quantidade(Long quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public ItemOrcamento build() {
            return new ItemOrcamento(this);
        }
    }
}
