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
       Delegacia delegacia;
       @Expose
       Bairro bairro;
       @Expose
       AreaIntegradaSeguranca ais;

       public DadosTerritoriais(Delegacia delegacia, Bairro bairro, AreaIntegradaSeguranca ais)
       {
              this.delegacia = delegacia;
              this.bairro = bairro;
              this.ais = ais;
       }

       public DadosTerritoriais() {
       }

       public Delegacia getDelegacia()
       {
              return delegacia;
       }

       public void setDelegacia(Delegacia delegacia)
       {
              this.delegacia = delegacia;
       }

       public Bairro getBairro()
       {
              return bairro;
       }

       public void setBairro(Bairro bairro)
       {
              this.bairro = bairro;
       }

       public AreaIntegradaSeguranca getAis() {
              return ais;
       }

       public void setAis(AreaIntegradaSeguranca ais) {
              this.ais = ais;
       }

}