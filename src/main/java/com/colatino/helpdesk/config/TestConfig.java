package com.colatino.helpdesk.config;

import com.colatino.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean // Toda vez que o perfil de TEST foi ativo, rodará o Bean de forma automática e instanciará dados no banco;
    public String instanciaDB(){
        this.dbService.instanciaDB();
        return "Perfil de teste rodado";
    }

}
