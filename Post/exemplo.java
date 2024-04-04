public class App {
    public static void main(String[] args) throws Exception {

        /*-----------------------------------------------------------
        |          posta um objeto para o webservice                |
        |   criado por: Henrique Costa          03/04/2024          |
        -----------------------------------------------------------*/

        RoloProd rolo = new RoloProd();
        rolo.ZI2_FILIAL = "14";
        rolo.ZI2_ROLO = "JVC4325";
        rolo.ZI2_OP = "181223";
        rolo.ZI2_PARTID = "00000000";
        rolo.ZI2_PROD = "14M040000150";
        rolo.ZI2_DESPRO = "OURELAS DIV CORES .";
        rolo.ZI2_PESLIQ = "17.51";
        rolo.ZI2_TARA = "0.10";
        rolo.ZI2_PESBRU = "17.61";
        rolo.ZI2_DATPES = "20231218";
        rolo.ZI2_HORPES = "15:38:44";
        rolo.ZI2_USUPES = "jmoura";

        PostObjLegacy postObj = new PostObjLegacy(rolo.getValores() , "MvcCApiM", "FORMZI2", "http://10.70.2.18:6074/rest/fwmodel/rolos");
        System.out.println(postObj.Post());
    }
}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

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
