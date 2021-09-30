package br.com.projeto_springboot.projeto_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto_springboot.projeto_spring.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "select u from Usuario u where lower(trim(u.nome)) like %?1%")
	public List<Usuario> buscarPorNome(String nome);
	

}
