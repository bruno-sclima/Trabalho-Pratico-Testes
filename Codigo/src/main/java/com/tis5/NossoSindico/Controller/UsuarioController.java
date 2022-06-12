package com.tis5.NossoSindico.Controller;

import com.tis5.NossoSindico.Service.UsuarioService;
import com.tis5.NossoSindico.domain.Apartamento;
import com.tis5.NossoSindico.domain.LoginUsuario;
import com.tis5.NossoSindico.domain.Usuario;
import com.tis5.NossoSindico.domain.UsuarioComApto;
import com.tis5.NossoSindico.repository.ApartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @Autowired
    ApartamentoRepository aptoRepository;

    @RequestMapping(method = RequestMethod.POST, value = "cadastro")
    public ResponseEntity<Usuario> cadastro(@RequestBody Usuario usuario) throws ParseException {
        return ResponseEntity.ok(service.createUsuario(Usuario.builder().nome(usuario.getNome()).sobrenome(usuario.getSobrenome()).email(usuario.getEmail()).senha(usuario.getSenha()).build()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario login) {
        Optional<Usuario> usuario = service.getUuarioByEmail(login.getEmail());
        if(usuario.isPresent() && login.getSenha().equals(usuario.get().getSenha())) return ResponseEntity.ok(usuario.get());
        else return new ResponseEntity<String>("Senha Cringe", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @RequestMapping(method = RequestMethod.POST, value = "att")
    public ResponseEntity<?> attUsuario(@RequestBody Usuario usuario) {
        System.out.println(usuario.toString());
        service.update(usuario);
        return new ResponseEntity<String>("Atualizei o Roberto", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "delete")
    public ResponseEntity<?> deleteUsuario(@RequestBody Usuario usuario){
        service.deleteUsuarioById(usuario.getId());
        return new ResponseEntity<String>("Josue deletado", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "getApto")
    public ResponseEntity<UsuarioComApto> getAptoUsuario(@RequestBody Usuario usuario){
        Optional<List<Apartamento>> lista = aptoRepository.findByUsuario(usuario);
        int nro = lista.get().stream().findFirst().get().getNumero();
        return ResponseEntity.ok(UsuarioComApto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .sobrenome(usuario.getSobrenome())
                .senha(usuario.getSenha())
                .nroApto(nro).build());
    }
}
