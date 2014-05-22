package ${package}.account;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
	
	@Value("${default.locale}")
	private String defaultLang;
	
	@Autowired
	AccountRepository accRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Account save(Account account) {
		account.setLanguage(defaultLang);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		entityManager.persist(account);
		return account;
	}
	
	public Account findByEmail(String email){
		return accRepo.findByEmail(email);
	}
	
	public Account findByUsername(String username){
		return accRepo.findByUsername(username);
	}

	
	
}
