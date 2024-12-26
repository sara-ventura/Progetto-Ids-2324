package it.unicam.cs.ids.GeoPlus.Model.Servizi;


import it.unicam.cs.ids.GeoPlus.Model.Auth.ExistingUserException;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiziAccount {

    @Autowired
    private AccountRepository accountRepository;


    public Account creaTurista(String email, String password, String username) throws ExistingUserException {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isPresent()) {
            throw new ExistingUserException();
        }
        Account newAccount = new Account(email, password, username, Ruoli.TURISTA_AUTENTICATO, null);
        accountRepository.save(newAccount);
        return newAccount;
    }

    public Account creaUtenteComune(String email, String password, String username, Ruoli ruolo, Comune comune) throws ExistingUserException {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isPresent()) {
            throw new ExistingUserException();
        }
        Account newAccount = new Account(email, password, username, ruolo, comune);
        accountRepository.save(newAccount);
        return newAccount;
    }

    public Account creaAmministratore(String email, String password, String username, Comune
            comune) throws ExistingUserException {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isPresent()) {
            throw new ExistingUserException();
        }
        Account newAccount = new Account(email, password, username, Ruoli.AMMINISTRATORE_COMUNALE, comune);
        accountRepository.save(newAccount);
        return newAccount;
    }


    public void salvaUtente(Account account) {
        accountRepository.save(account);
    }


    public Optional<Account> getAccount(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public List<Account> getListaUtentiComune(Comune comune) {
        return accountRepository.findAllByComuneAppartenenza(comune);
    }


}
