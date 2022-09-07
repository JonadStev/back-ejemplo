package com.tienda.security.controller;

import com.tienda.Mail.EnvioEmail;
import com.tienda.security.dto.ReseteoContrasenia;
import com.tienda.security.service.RolService;
import com.tienda.security.service.UsuarioService;
import com.tienda.security.dto.JwtDto;
import com.tienda.security.dto.LoginUsuario;
import com.tienda.security.dto.NuevoUsuario;
import com.tienda.security.enums.RolNombre;
import com.tienda.security.jwt.JwtProvider;
import com.tienda.security.model.Rol;
import com.tienda.security.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    EnvioEmail envioEmail;


    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody NuevoUsuario nuevoUsuario){
        String passwordDelivery = "";
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity("El correo ya existe", HttpStatus.BAD_REQUEST);
        Usuario usuario = new Usuario(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getEmail(),
                nuevoUsuario.getNombreUsuario(),
                passwordEncoder.encode(nuevoUsuario.getPassword()),
                nuevoUsuario.getDireccion(),
                nuevoUsuario.getEstado()
        );
        Set<Rol> roles = new HashSet<>();
        if(nuevoUsuario.getRoles().contains("delivery")){
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_DELIVERY).get());
            passwordDelivery = this.getRandomString(10);
            usuario.setPassword(passwordEncoder.encode(passwordDelivery));
            try {
                envioEmail.sendEmail(usuario.getEmail(),"CREDENCIALES DEL SISTEMA", "Este es su usuario y contraseña: "+usuario.getNombreUsuario()+" - "+passwordDelivery);
            }catch (Exception e){
                System.out.println("Ocurrio un error en el envio de correo: "+e);
            }
        }
        else
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin")){
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_DELIVERY).get());
            passwordDelivery = this.getRandomString(10);
            usuario.setPassword(passwordEncoder.encode(passwordDelivery));
            try {
                envioEmail.sendEmail(usuario.getEmail(),"CREDENCIALES DEL SISTEMA", "Este es su usuario y contraseña: "+usuario.getNombreUsuario()+" - "+passwordDelivery);
            }catch (Exception e){
                System.out.println("Ocurrio un error en el envio de correo: "+e);
            }
        }

        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity("Usuario guardado", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUsuario loginUsuario){
        Optional<Usuario> u = usuarioService.getByNombreUsuario(loginUsuario.getNombreUsuario());
        if(u.get().getEstado().equalsIgnoreCase("INACTIVO")){
            return new ResponseEntity(new JwtDto("NO_VALIDO"), HttpStatus.OK);
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/restablecerPassword")
    public Map<String, String> resetPassword(@RequestBody ReseteoContrasenia reseteoContrasenia){
        Map<String, String> map = new HashMap<>();
        if(!usuarioService.existsByNombreUsuario(reseteoContrasenia.getUsername())){
            map.put("message", "El nombre de usuario no existe en nuestro sistema");
            return map;
        }
        System.out.println("NO ENTRO EN LA VALIDACION");
        Optional<Usuario> usuario = usuarioService.getByNombreUsuario(reseteoContrasenia.getUsername());
        usuario.get().setPassword(passwordEncoder.encode(reseteoContrasenia.getPassword()));
        usuarioService.save(usuario.get());
        map.put("message", "Su contraseña ha sido modificada con éxito");
        return map;
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtDto> refreshToken(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }

    private String getRandomString(int i)
    {
        // bind the length
        byte[] bytearray;
        bytearray = new byte[256];
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray);

        mystring = new String(bytearray, Charset.forName("UTF-8"));

        thebuffer = new StringBuffer();

        //remove all spacial char
        theAlphaNumericS = mystring.replaceAll("[^A-Z0-9]", "");

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) {
            if (Character.isLetter(theAlphaNumericS.charAt(m))
                    && (i > 0)
                    || Character.isDigit(theAlphaNumericS.charAt(m))
                    && (i > 0)) {
                thebuffer.append(theAlphaNumericS.charAt(m));
                i--;
            }
        }
        return thebuffer.toString();
    }

}
