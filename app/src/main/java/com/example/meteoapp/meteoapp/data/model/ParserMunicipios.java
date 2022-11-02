package com.example.meteoapp.meteoapp.data.model;

import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/*
 * Clase para parsear el XML alojado en assets > codigos_municipios.xml
 * IMPORTANTE. Entre una etiqueta y otra siempre habra texto (aunque sea un salto de linea)
 * Lo anterior provoca que la variable event=4 despues de leer una etiqueta
 * Cuando event=4 tendremos que la variable tag=null
 */
public class ParserMunicipios {

    private static LinkedHashMap<String, String> diccionarioMunicipios;

    public ParserMunicipios(InputStream inputStream) throws XmlPullParserException, IOException {
        diccionarioMunicipios = new LinkedHashMap<>();

        XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = parserFactory.newPullParser();
        //Le pasamos el inputStream al parser. No utilizamos ningun encoding (null)
        parser.setInput(inputStream, null);

        //comienzoDocumento = 0, finalDocumento = 1, aperturaEtiqueta = 2, cierreEtiqueta = 3, texto = 4
        int event = parser.getEventType();

        String tag = "";
        String codigoMunicipio = ""; //Es la concatenacion de los numeros en la Columna2 y Columna3
        String municipio = ""; //Aparecera en Columna5

        while (event != XmlPullParser.END_DOCUMENT) {
            tag = parser.getName(); //Guardamos el nombre de la etiqueta

            if (event == XmlPullParser.START_TAG) {
                switch (tag) {
                    case "Column2":
                    case "Column3":
                        event = parser.next();
                        codigoMunicipio += parser.getText();
                        break;
                    case "Column5":
                        event = parser.next();
                        municipio = parser.getText();
                        break;
                    default: break;
                }
            } else if (event == XmlPullParser.END_TAG && tag.equals("Row")) {
                //Cuando llegamos a la etiqueta de cierre Row: </Row>
                diccionarioMunicipios.put(municipio, codigoMunicipio);
                //Reseteamos los valores para la siguiente vez que encuentre Row
                codigoMunicipio = "";
                municipio = "";
            }
            event = parser.next();
        }
        //Borramos la primera entrada del diccionario. En el XML es <Row  index="1">
        diccionarioMunicipios.remove("NOMBRE");
    }

    public ArrayList<String> getListaMunicipios(){
        ArrayList<String> llaves = new ArrayList<>();
        llaves.addAll(diccionarioMunicipios.keySet());
        return llaves;
    }

    public static String getCodigoMunicipio(String llave){
        return diccionarioMunicipios.get(llave);
    }
}
