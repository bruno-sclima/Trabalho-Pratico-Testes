package com.tis5.NossoSindico.Service;

import com.tis5.NossoSindico.domain.Usuario;
import com.tis5.NossoSindico.domain.UsuarioComApto;
import com.tis5.NossoSindico.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    public Usuario createUsuario(Usuario usuario) {
        System.out.println(usuario.toString());
        return repository.save(usuario);
    }

    public Usuario getUsuarioById(long id) {
        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isPresent()) {
            return usuario.get();

        } else return null;
    }

    public Optional<Usuario> getUuarioByEmail(String email){
        Optional<Usuario> usuario = repository.findByEmail(email);

        if(usuario.isPresent()) return usuario;
        else return Optional.empty();
    }

    public List<Usuario> listUsuarios() {
        List<Usuario> lista = repository.findAll();
        return (lista.size() > 0 ? lista : null);
    }

    public Usuario update(Usuario usuario) {
        Optional<Usuario> optional = repository.findById(usuario.getId());
        if (optional.isPresent()) {
            Usuario usuarioAtt = optional.get();
            usuarioAtt.setSobrenome(usuario.getSobrenome());
            usuarioAtt.setEmail(usuario.getEmail());
            usuarioAtt.setSenha(usuarioAtt.getSenha());
            usuarioAtt.setNome(usuario.getNome());
            usuarioAtt = repository.save(usuarioAtt);
            return usuarioAtt;
        } else return null;
    }

    public void deleteUsuarioById(long id){
        Optional<Usuario> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(optional.get().getId());
        }
    }
}
