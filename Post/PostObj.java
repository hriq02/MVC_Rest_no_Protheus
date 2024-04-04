import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class PostObjLegacy {
    public String[] campos = {"ZI2_FILIAL","ZI2_ROLO","ZI2_OP","ZI2_PARTID","ZI2_PROD","ZI2_DESPRO","ZI2_PESLIQ","ZI2_TARA","ZI2_PESBRU","ZI2_DATPES","ZI2_HORPES" ,"ZI2_USUPES"};
    public String[] valores;
    private String mvc = "MvcCApiM";
    private String form = "FORMZI2";
    private String url;
    private String json;
    public PostObjLegacy(String[] _valores, String _mvc, String _form,String _url){
        mvc = _mvc;
        form = _form;
        valores = _valores;
        url = _url;
    }

    /**
    * esse Metodo é responsavel por postar um objeto para o webservice baseado nos valores dados
    *
    * @return         	ira retornar uma String com o resultado do POST request
    */
    public String Post() throws IOException{
        json = getPostJson();
        String returnMessage = "";

        //a gabiarra e o seguinte, cria um diretorio temp com json e bat, e roda o bat usando o curl
        String tmpdir = Files.createTempDirectory("tempPostJV").toFile().getAbsolutePath();

        FileWriter jsonFile = new FileWriter(tmpdir + "/post.json");
        FileWriter batchFile = new FileWriter(tmpdir + "/post.bat");

        jsonFile.write(json);
        batchFile.write("cd /d %~dp0\ncurl \"" + url + "\" -H \"Content-Type:application/json\" -d @post.json");

        batchFile.close();
        jsonFile.close();

        //String[] command = { tmpdir + "/post.bat"};
        Process proc = Runtime.getRuntime().exec(new String[] { tmpdir + "/post.bat"});

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        // Read the output from the command
        System.out.println("output from command:\n");
        String s = null;

        while ((s = stdInput.readLine()) != null) {
            returnMessage += s + "\n";
        }

        // Read any errors from the attempted command
        System.out.println("errors of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            //System.out.println(s);
            returnMessage += s + "\n";
        }

        return returnMessage;
    }

    /**
    * ira retornar um json em valor STRING para consumos de APIs
    *
    * @return         	retorna String no Formato Json
    */
    public String getPostJson(){
        String json = "{\"id\":\""+ mvc + "\",\"operation\":3,\"models\":[{\"id\":\"" + form + "\",\"modeltype\": \"FIELDS\",\"fields\":[";
        json += GetItems();
        json += "]}]}";

        return json;
    }

    /**
    * funcao com a responsabilidade de montar os fields
    *
    * @return         	retorna o campo
    */
    private String GetItems(){
        String jsonRt = "";
        for (int i = 0; i <= campos.length - 1; i++) {
            jsonRt += "{\"id\":\"" + campos[i] + "\",\"order\":" + (i+1) + ",\"value\":\"" + valores[i] + "\"}";
            if(i != campos.length - 1){
                jsonRt += ",";
            }
        }
        return jsonRt;
        
    }
}
