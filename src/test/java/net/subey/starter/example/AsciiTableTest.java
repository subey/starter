package net.subey.starter.example;

import com.bethecoder.ascii_table.ASCIITable;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * Created by subey on 4/26/17.
 */
public class AsciiTableTest {

    @Test
    public void keyValue(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("aa", "123");
        map.put("bb", "456");
        String t1 = ASCIITable.getInstance().getTable("Header", map);

        map = new LinkedHashMap<>();
        map.put("aa", "123");
        String t2 = ASCIITable.getInstance().getTable(new String[]{"Key", "Value"}, map);

        ASCIITable.getInstance().printInlineString(t1,t2, " ");
    }

}
