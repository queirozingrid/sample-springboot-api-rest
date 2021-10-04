package br.com.projeto_springboot.projeto_spring.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import br.com.projeto_springboot.projeto_spring.model.Usuario;
import br.com.projeto_springboot.projeto_spring.repository.UsuarioRepository;

@RestController
public class GreetingsController {
	@Autowired
	private UsuarioRepository usuarioRepository;

    @GetMapping(value = "listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario user){
    	Usuario user2 = usuarioRepository.save(user);
    	return new ResponseEntity<Usuario>(user2, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "remover")
    @ResponseBody
    public ResponseEntity<String> remover(@RequestParam Long iduser){
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("User deletado com sucesso!", HttpStatus.OK);
    }
    
    @GetMapping(value = "buscarporid")
    @ResponseBody
    public ResponseEntity<Usuario> buscarPorId(@RequestParam(name="iduser") Long iduser){
    	Usuario user = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario user){
    	if(user.getId() == null) {
    		return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
    	}
    	
    	Usuario user2 = usuarioRepository.save(user);
    	return new ResponseEntity<Usuario>(user2, HttpStatus.OK);
    }
    
    @GetMapping(value = "buscarpornome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestBody @RequestParam(name="nome") String nome){
    	
    	List<Usuario> users = usuarioRepository.buscarPorNome(nome.trim().toLowerCase());
    	
    	return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
    }
    
}
