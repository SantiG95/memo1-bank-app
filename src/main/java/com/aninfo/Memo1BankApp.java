package com.aninfo;

import com.aninfo.model.Account;
import com.aninfo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.aninfo.model.Transaction;
import com.aninfo.service.TransactionService;

import javax.swing.text.html.Option;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class Memo1BankApp {

	@Autowired
	private AccountService accountService;
	private TransactionService transactionService;

	public static void main(String[] args) {
		SpringApplication.run(Memo1BankApp.class, args);
	}

	@PostMapping("/accounts")
	@ResponseStatus(HttpStatus.CREATED)
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}

	@GetMapping("/accounts")
	public Collection<Account> getAccounts() {
		return accountService.getAccounts();
	}

	@GetMapping("/accounts/{cbu}")
	public ResponseEntity<Account> getAccount(@PathVariable Long cbu) {
		Optional<Account> accountOptional = accountService.findById(cbu);
		return ResponseEntity.of(accountOptional);
	}

	@PutMapping("/accounts/{cbu}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable Long cbu) {
		Optional<Account> accountOptional = accountService.findById(cbu);

		if (!accountOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		account.setCbu(cbu);
		accountService.save(account);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/accounts/{cbu}")
	public void deleteAccount(@PathVariable Long cbu) {
		accountService.deleteById(cbu);
	}

	@PutMapping("/accounts/{cbu}/withdraw")
	public Account withdraw(@PathVariable Long cbu, @RequestParam Double sum) {
		return accountService.withdraw(cbu, sum);
	}

	@PutMapping("/accounts/{cbu}/deposit")
	public Account deposit(@PathVariable Long cbu, @RequestParam Double sum) {
		return accountService.deposit(cbu, sum);
	}

	//----------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/transactions/deposits")
	@ResponseStatus(HttpStatus.CREATED)
	public Transaction createDeposit(@RequestBody Transaction transaction){
		return accountService.createDeposit(transaction);
	}

	@PostMapping("/transactions/withdraws")
	@ResponseStatus(HttpStatus.CREATED)
	public Transaction createWithDraw(@RequestBody Transaction transaction){
		return accountService.createWithdraw(transaction);
	}

	@GetMapping("/account/transactions/{accountCbu}")
	public Collection<Transaction> getTransactionByCbu(@PathVariable Long accountCbu) {
		return accountService.getTransactionsByCbu(accountCbu);
	}

	@GetMapping("/transactions/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
		Optional<Transaction> transactionOptional = accountService.getTransactionById(id);
		return ResponseEntity.of(transactionOptional);
	}

	@DeleteMapping("/transactions/{id}")
	public void deleteTransaction(@PathVariable Long id) {
		accountService.deleteTransaction(id);
	}
	//----------------------------------------------------------------------------------------------------------------------------

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
