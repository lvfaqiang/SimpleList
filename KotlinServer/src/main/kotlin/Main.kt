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
        resp.writer.write("Hello, Kotlin~!")
    }
}