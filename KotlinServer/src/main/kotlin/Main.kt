import jdk.nashorn.internal.parser.JSONParser
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.json.JsonObject
import javax.jws.WebService
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Main
 * @author lvfq
 * @date 2018/8/27 下午2:10
 * @mainFunction :
 *
 */

@WebServlet(name = "Hello", value = ["/hello"])
class Main : HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val name = req.getParameter("name")
        val age = req.getParameter("age")
        if (name == "ZhangSan") {
            resp.writer.write("{\"data\":\"Ok\"}")
        } else {
            resp.writer.write("{\"error\":\"Your Name is No\'t found\"}")
        }

        val parameterNames = req.parameterNames
        while (parameterNames?.hasMoreElements() == true) {
            val e = parameterNames.nextElement()
            val value = req.getParameter(e)
            System.out.println("-- $e : $value")
        }

    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {

        req?.characterEncoding = "utf-8";
        resp?.contentType = "text/html;charset=utf-8";

        val parameterNames = req?.parameterNames
        while (parameterNames?.hasMoreElements() == true) {
            val e = parameterNames.nextElement()
            val value = req.getParameter(e)
            System.out.println("-- $e : $value")
        }

        resp?.writer?.write(UserInfo("fq", "20", "Man", "ITer").toString())
    }
}

data class UserInfo(
        var name: String,
        var age: String,
        var gender: String,
        var job: String
) {
    override fun toString(): String {
        return "UserInfo(name='$name', age='$age', gender='$gender', job='$job')"
    }
}