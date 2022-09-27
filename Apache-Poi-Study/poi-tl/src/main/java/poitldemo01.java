import com.deepoove.poi.XWPFTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName &{NAME}
 * @Description poitldemo01
 * @Author code-ran
 * @Date 2022/8/5 00:35
 * @Version1.0
 */
public class poitldemo01 {
    public static void main(String[] args) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile("poi-tl/template.docx").render(new HashMap<String, Object>() {{
            put("title", "Hi, poi-tl Word模板引擎");
        }});
        template.writeAndClose(new FileOutputStream("output.docx"));
        System.out.println("操作成功");
    }
}
