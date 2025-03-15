package banking.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {

        public Object parse (String data) {
                data = data.trim();
                String dictionaryRegex = "^\\{.*\\}$";
                String listRegex = "^\\[.*\\]$";
                if(data.matches(dictionaryRegex)) {
                        return parseDictionary(data.substring(1, data.length() - 1).trim());
                }
                else if(data.matches(listRegex)) {
                        return parseLIst(data.substring(1, data.length() - 1).trim());
                }
                return data;
        }

        public Map<String, Object> parseDictionary(String data) {
                StringBuilder item = new StringBuilder();
                int counter = 0;
                HashMap<String, Object> map = new HashMap<>();
                data += ",";
                for(char c : data.toCharArray()) {
                        if(c == '{' || c == '[')
                                counter ++;
                        if(c == '}' || c == ']')
                                counter --;
                        if(c == ',' && counter == 0) {
                                String[] pair = item.toString().split(":", 2);
                                map.put(pair[0], parse(pair[1]));
                                item = new StringBuilder();
                        }
                        else {
                                item.append(c);
                        }
                }
                return map;
        }

        public ArrayList<Object> parseLIst(String data) {
                ArrayList<Object> list = new ArrayList<>();
                StringBuilder item = new StringBuilder();
                int counter = 0;
                data += ",";
                for(char c : data.toCharArray()) {
                        if(c == '{' || c == '[')
                                counter ++;
                        if(c == '}' || c == ']')
                                counter --;
                        if(c == ',' && counter == 0) {
                                list.add(item);
                                item = new StringBuilder();
                        }
                        else {
                                item.append(c);
                        }
                }
                return list;
        }


}
