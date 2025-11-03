package io.github.app.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "Orcamentos")
public class Orcamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
    @Column( updatable = false)
    private LocalDateTime dateCreated;

    @Version
    @Column( updatable = false)  
    private Long version;

    @Column(name = "id_cliente", insertable = false, updatable = false)
    private Long idCliente;
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Situacao situacao;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
			CascadeType.MERGE}, orphanRemoval = true)
	@JoinColumn(name = "id_orcamento", nullable = false)
	private Set<ItemOrcamento> items = new HashSet<ItemOrcamento>();

	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal precoTotal;

	@Deprecated
	public Orcamento() {
	}

	private Orcamento(Builder builder) {
		this.id = builder.id;
		
		this.situacao = builder.situacao != null
				? builder.situacao
				: Situacao.ABERTO;
		
		this.items = builder.items;
		this.precoTotal = builder.precoTotal != null
				? builder.precoTotal
				: BigDecimal.ZERO;
		
	}



	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public Set<ItemOrcamento> getItems() {
		return items;
	}

	public Long getVersion() {
		return version;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public void setItems(Set<ItemOrcamento> items) {
		this.items = items;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
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
		Orcamento other = (Orcamento) obj;
		return Objects.equals(id, other.id);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		
		private Set<ItemOrcamento> items;
		private Situacao situacao;
		private BigDecimal precoTotal;
		
		

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder situacao(Situacao situacao) {
			this.situacao = situacao;
			return this;
		}
		public Builder precoTotal(BigDecimal precoTotal) {
			this.precoTotal = precoTotal;
			return this;
		}
	

		public Builder item(ItemOrcamento item) {
			if (item == null)
				return this;
			if (items == null) {
				this.items = new HashSet<ItemOrcamento>();
			}
			this.items.add(item);

			return this;
		}
		public Builder item(Collection<ItemOrcamento> items) {
			if (items == null)
				return this;
			if (this.items == null) {
				this.items = new HashSet<ItemOrcamento>();
			}
			this.items.addAll(items);
			return this;
		}

		public Orcamento build() {
			return new Orcamento(this);

		}
	}
	public enum Situacao {
		ABERTO, CANCELADO, APROVADO
	}
}
