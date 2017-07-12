package Model;

import com.orm.SugarRecord;

import java.util.List;

import Enums.OrientacaoGeografica;
import Enums.TipoVeiculo;

/**
 * Created by Pefoce on 30/05/2017.
 */

public class Veiculo extends SugarRecord<Veiculo>{


    TipoVeiculo tipoVeiculo;



    String placa;


    String modelo;
    Boolean isCulpado;

    OrientacaoGeografica orientacaoPartida;
    OrientacaoGeografica orientacaoChegada;


    List<Dano> danos;

    public Veiculo(String placa) {
        this.placa = placa;
    }

    public Veiculo()
    {

    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Boolean getCulpado() {
        return isCulpado;
    }

    public void setCulpado(Boolean culpado) {
        isCulpado = culpado;
    }

    public OrientacaoGeografica getOrientacaoPartida() {
        return orientacaoPartida;
    }

    public void setOrientacaoPartida(OrientacaoGeografica orientacaoPartida) {
        this.orientacaoPartida = orientacaoPartida;
    }

    public OrientacaoGeografica getOrientacaoChegada() {
        return orientacaoChegada;
    }

    public void setOrientacaoChegada(OrientacaoGeografica orientacaoChegada) {
        this.orientacaoChegada = orientacaoChegada;
    }

    public List<Dano> getDanos() {
        return danos;
    }

    public void setDanos(List<Dano> danos) {
        this.danos = danos;
    }


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Created by Pefoce on 23/06/2017.
     */

}
