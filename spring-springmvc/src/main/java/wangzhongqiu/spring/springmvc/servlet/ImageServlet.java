package wangzhongqiu.spring.springmvc.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ImageServlet extends HttpServlet {
    private static Log log = LogFactory.getLog(FileUtil.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        StringBuffer url = request.getRequestURL();

        int index = url.indexOf("/upload/");

        String upload = url.substring(index);

        InputStream is = null;
        try {
            is = new FileInputStream(new File(upload)); // 文件输入流

            if (is != null) {
                byte[] bs = new byte[4096];
                int len;
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
            }

        } catch (Exception e) {
            log.error("读取图片失败", e);
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
