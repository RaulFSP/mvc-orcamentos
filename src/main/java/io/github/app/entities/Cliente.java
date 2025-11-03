package io.github.app.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Boolean active;
	@Version
	@Column(nullable = false )
	private Long version;

	@CreationTimestamp
	@Column(updatable = false,nullable = false )
	private LocalDateTime dateCreated;

	@OneToMany(cascade = {CascadeType.PERSIST,
			CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Set<Orcamento> orcamentos = new HashSet<Orcamento>();

	@Deprecated
	public Cliente() {
	}

	private Cliente(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.active = builder.active;
		this.orcamentos = builder.orcamentos;
		this.version = builder.version;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getVersion() {
		return version;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Boolean getActive() {
		return active;
	}

	public Set<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setOrcamentos(Set<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", name=" + name + ", active=" + active
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private Long id;
		private String name;
		private Boolean active;
		private Set<Orcamento> orcamentos;
		private Long version;
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder version(Long version) {
			this.version = version;
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

		public Builder orcamento(Orcamento orcamento) {
			if (orcamentos == null)
				return this;
			if (this.orcamentos == null) {
				this.orcamentos = new HashSet<Orcamento>();
			}
			this.orcamentos.add(orcamento);
			return this;
		}
		public Builder orcamento(Collection<Orcamento> orcamentos) {
			if (orcamentos == null)
				return this;
			if (this.orcamentos == null) {
				this.orcamentos = new HashSet<Orcamento>();
			}
			this.orcamentos.addAll(orcamentos);
			return this;
		}
		public Cliente build() {
			return new Cliente(this);
		}
	}
}
