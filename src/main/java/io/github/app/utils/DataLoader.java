package io.github.app.utils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import io.github.app.entities.Cliente;
import io.github.app.entities.Item;
import io.github.app.entities.ItemOrcamento;
import io.github.app.entities.Orcamento;
import io.github.app.entities.Orcamento.Situacao;
import io.github.app.repositories.ClienteRepository;
import io.github.app.repositories.ItemOrcamentoRepository;
import io.github.app.repositories.ItemRepository;
import io.github.app.repositories.OrcamentoRepository;
import jakarta.transaction.Transactional;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ItemOrcamentoRepository itemOrcamentoRepository;

	@Autowired
	private OrcamentoRepository orcamentoRepository;

	@Override
	
	public void run(String... args) throws Exception {
		
	}
	private void initDb() {
		Cliente cliente = Cliente.builder()
                .name("Fulano")
                .active(true)
                .build();
        clienteRepository.save(cliente);

        
        Item item = Item.builder()
                .name("Tesoura")
                .preco(new BigDecimal("15.00"))
                .active(true)
                .build();
        itemRepository.save(item);

        ItemOrcamento itemOrcamento = ItemOrcamento.builder().item(item).preco(BigDecimal.valueOf(10.0)).quantidade(15L).build();
        
        Orcamento orcamento = Orcamento.builder()
                .situacao(Orcamento.Situacao.ABERTO)
                .precoTotal(BigDecimal.ZERO)
                .build();

        cliente = clienteRepository.findByIdWithOrcamentos(cliente.getId()).orElseThrow();
        cliente.getOrcamentos().add(orcamento);
        clienteRepository.save(cliente);
        
        
        
        orcamento = orcamentoRepository.findByIdWithItems(1L).orElseThrow();
        orcamento.getItems().add(itemOrcamento);
        orcamento.setPrecoTotal(itemOrcamento.getPreco().multiply(BigDecimal.valueOf(itemOrcamento.getQuantidade())));
        orcamentoRepository.save(orcamento);
        
        orcamento = orcamentoRepository.findByIdWithItems(1L).orElseThrow();
        System.out.println(orcamento.getItems().size());
        
        orcamento.getItems().clear();
        System.out.println(orcamento.getItems().size());
        orcamentoRepository.save(orcamento);
	}



}
