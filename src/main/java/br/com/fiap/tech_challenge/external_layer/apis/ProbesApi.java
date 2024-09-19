package br.com.fiap.tech_challenge.external_layer.apis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech_challenge.external_layer.apis.interfaces.IProbesApi;

@RestController
public class ProbesApi implements IProbesApi {

    @Override
    public ResponseEntity<Void> consultarLivenessProbe() {
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> consultarReadinessProbe() {
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> consultarStartupProbe() {
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
