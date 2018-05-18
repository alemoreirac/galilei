package Model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import Enums.AreaIntegradaSeguranca;

/**
 * Created by Pefoce on 16/08/2017.
 */

public class DadosTerritoriais extends SugarRecord
{
       @Expose
       String delegacia;
       @Expose
       String bairro;
       @Expose
       AreaIntegradaSeguranca ais;

       public DadosTerritoriais() {
       }

       public String getDelegacia() {
              return delegacia;
       }

       public void setDelegacia(String delegacia) {
              this.delegacia = delegacia;
       }

       public String getBairro() {
              return bairro;
       }

       public void setBairro(String bairro) {
              this.bairro = bairro;
       }

       public AreaIntegradaSeguranca getAis() {
              return ais;
       }

       public void setAis(AreaIntegradaSeguranca ais) {
              this.ais = ais;
       }

       public DadosTerritoriais(String delegacia, String bairro, AreaIntegradaSeguranca ais) {
              this.delegacia = delegacia;
              this.bairro = bairro;
              this.ais = ais;
       }
}
