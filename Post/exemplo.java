public class App {
    public static void main(String[] args) throws Exception {

        /*-----------------------------------------------------------
        |          posta um objeto para o webservice                |
        |   criado por: Henrique Costa          03/04/2024          |
        -----------------------------------------------------------*/

        RoloProd rolo = new RoloProd();
        rolo.ZI2_FILIAL = "";
        rolo.ZI2_ROLO = "";
        rolo.ZI2_OP = "";
        rolo.ZI2_PARTID = "";
        rolo.ZI2_PROD = "";
        rolo.ZI2_DESPRO = "";
        rolo.ZI2_PESLIQ = "";
        rolo.ZI2_TARA = "";
        rolo.ZI2_PESBRU = "";
        rolo.ZI2_DATPES = "20231218";
        rolo.ZI2_HORPES = "15:38:44";
        rolo.ZI2_USUPES = "";

        PostObj postObj = new PostObj(rolo.getValores() , "MvcCApiM", "FORMZI2", "url/rest/fwmodel/rolos");
        System.out.println(postObj.Post());
    }
}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

//essa é uma classe basica que serve mais pra alimentar o PostObj
//não é necessaria no fonte, mas fiz para facilitar a leitura
//ela só é responsavel em retornar um array de strings

public class RoloProd{
    public String ZI2_FILIAL = "";
    public String ZI2_ROLO = "";
    public String ZI2_OP = "";
    public String ZI2_PARTID = "";
    public String ZI2_PROD = "";
    public String ZI2_DESPRO = "";
    public String ZI2_PESLIQ = "";
    public String ZI2_TARA = "";
    public String ZI2_PESBRU = "";
    public String ZI2_DATPES = "";
    public String ZI2_HORPES = "";
    public String ZI2_USUPES = "";

    public RoloProd(){
    }
    public String[] getValores(){
        return new String[] {ZI2_FILIAL,ZI2_ROLO,ZI2_OP,ZI2_PARTID,ZI2_PROD,ZI2_DESPRO,ZI2_PESLIQ,ZI2_TARA,ZI2_PESBRU,ZI2_DATPES,ZI2_HORPES ,ZI2_USUPES};
    }
}
