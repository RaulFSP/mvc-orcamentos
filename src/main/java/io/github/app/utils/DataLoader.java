package io.github.app.utils;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionMessage.ItemsBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import io.github.app.entities.Cliente;
import io.github.app.entities.Item;
import io.github.app.entities.ItemOrcamento;
import io.github.app.entities.Orcamento;
import io.github.app.repositories.ClienteRepository;
import io.github.app.repositories.ItemRepository;
import io.github.app.repositories.OrcamentoRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OrcamentoRepository orcamentoRepository;

	@Override

	public void run(String... args) throws Exception {
		initDb();
	}
	private void initDb() {

		insertClientes();
		insertItems();
		insertOrcamentos();
		insertItemOrcamentos();

	}

	public void insertClientes() {
		var clientes = List.of(
				Cliente.builder().name("Fulano").active(true).build(),
				Cliente.builder().name("Beltrano").active(true).build(),
				Cliente.builder().name("Ciclano").active(true).build());
		clienteRepository.saveAll(clientes);
	}

	public void insertItems() {
		List<Item> items = List.of(
				Item.builder().name("Caneta Esferográfica")
						.preco(new BigDecimal("2.50")).active(true).build(),

				Item.builder().name("Caderno Universitário")
						.preco(new BigDecimal("22.90")).active(true).build(),

				Item.builder().name("Mochila Reforçada")
						.preco(new BigDecimal("150.00")).active(true).build(),

				Item.builder().name("Lápis Grafite HB")
						.preco(new BigDecimal("1.25")).active(true).build(),

				Item.builder().name("Borracha Branca")
						.preco(new BigDecimal("1.80")).active(true).build(),

				Item.builder().name("Régua 30cm").preco(new BigDecimal("4.50"))
						.active(true).build(),

				Item.builder().name("Apontador com Depósito")
						.preco(new BigDecimal("3.75")).active(true).build(),

				Item.builder().name("Pasta Catálogo")
						.preco(new BigDecimal("18.00")).active(true).build(),

				Item.builder().name("Corretivo Líquido")
						.preco(new BigDecimal("6.10")).active(true).build(),

				Item.builder().name("Post-it Amarelo")
						.preco(new BigDecimal("9.99")).active(true).build()

		);
		itemRepository.saveAll(items);
		
	}

	public void insertOrcamentos() {
		Orcamento orcamento1 = Orcamento.builder()
				.situacao(Orcamento.Situacao.ABERTO).precoTotal(BigDecimal.ZERO)
				.build();
		var fulano = clienteRepository.findByIdWithOrcamentos(1L).orElseThrow();
		fulano.getOrcamentos().add(orcamento1);
		clienteRepository.save(fulano);

		Orcamento orcamento2 = Orcamento.builder()
				.situacao(Orcamento.Situacao.ABERTO).precoTotal(BigDecimal.ZERO)
				.build();
		var beltrano = clienteRepository.findByIdWithOrcamentos(2L)
				.orElseThrow();
		beltrano.getOrcamentos().add(orcamento2);
		clienteRepository.save(beltrano);

		Orcamento orcamento3 = Orcamento.builder()
				.situacao(Orcamento.Situacao.ABERTO).precoTotal(BigDecimal.ZERO)
				.build();
		var ciclano = clienteRepository.findByIdWithOrcamentos(3L)
				.orElseThrow();
		ciclano.getOrcamentos().add(orcamento3);
		clienteRepository.save(ciclano);
	}

	public void insertItemOrcamentos() {
		var orcamentos = orcamentoRepository.findAllWithItems();
		var items = itemRepository.findAllByActiveTrue();
		List<ItemOrcamento> itemsOrcamentos = new LinkedList<ItemOrcamento>();
		Random random = new Random();
		for (int i = 0; i < items.size(); i++) {

			var qtd = random.nextLong(10) + 1;
			var item = ItemOrcamento.builder().item(items.get(i))
					.preco(items.get(i).getPreco()).quantidade(qtd).build();
			itemsOrcamentos.add(item);
			
		}
		itemsOrcamentos.forEach(System.out::println);
		for (int i = 0; i < itemsOrcamentos.size(); i++) {
			var idOrcamento = random.nextInt(3);
			var orcamento = orcamentos.get(idOrcamento);
			var item = itemsOrcamentos.get(i);
			orcamento.getItems().add(item);
			orcamento.calcPrecoTotal();
			
		}
		orcamentoRepository.saveAll(orcamentos);
	}
}
