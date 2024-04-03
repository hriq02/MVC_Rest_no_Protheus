import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;



/*-----------------------------------------------------------
|   cria um objeto que formata um json e o posta no         |
|   webservice da TOTVS MVC API                             |
|                                                           |  
|   criado por: Henrique Costa              03/04/2024      |
-------------------------------------------------------------*/

/**
* esse objeto formata um json e o posta no webservice no modelo MVC API
*
* @author Henrique Costa
*/
public class PostObj {
    public String[] campos;
    public String[] valores;
    private String mvc = "MvcCApiM";
    private String form = "FORMZI2";
    private int operation = 3;
    private URL url;
    private String json;
    @SuppressWarnings("deprecation")
    public PostObj(String[] _campos, String[] _valores, int _operation, String _mvc, String _form,String _url) throws MalformedURLException{
        mvc = _mvc;
        form = _form;
        campos = _campos;
        valores = _valores;
        operation = _operation;
        url = new URL(_url);
    }
    /**
     * esse Metodo é responsavel por postar um objeto para o webservice baseado nos valores dados
     *
     * @return         	ira retornar uma String com o resultado do POST request
     */

    public String Post() throws IOException{
        json = getPostJson();
        String rs = "failed to post";
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        try {
            con.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
            return rs;
        }
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            rs = response.toString();
        }
        return rs;
    }

    /**
    * ira retornar um json em valor STRING para consumos de APIs
    *
    * @return         	retorna String no Formato Json
    */
    public String getPostJson(){
        String json = "{\"id\":\""+ mvc + "\",\"operation\":" + operation + ",\"models\":[{\"id\":\"" + form + "\",\"modeltype\": \"FIELDS\",\"fields\":[";
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
