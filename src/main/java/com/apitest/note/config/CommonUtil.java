package com.apitest.note.config;

import org.commonmark.node.Node;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;
import org.commonmark.parser.Parser;
/**
 * <br>package name   : com.apitest.note.config
 * <br>file name      : CommonUtil
 * <br>date           : 2024-08-26
 * <pre>
 * <span style="color: white;">[description]</span>
 *
 * </pre>
 * <pre>
 * <span style="color: white;">usage:</span>
 * {@code
 *
 * } </pre>
 * <pre>
 * modified log :
 * ====================================================
 * DATE           AUTHOR               NOTE
 * ----------------------------------------------------
 * 2024-08-26        jack8              init create
 * </pre>
 */
@Component
public class CommonUtil {
    public String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
