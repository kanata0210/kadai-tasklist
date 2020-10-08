package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        List<Task> tasks = em.createNamedQuery("getAllTasklist", Task.class)
                                   .getResultList();

        /*<Task>はmodelsパッケージにある「Task」クラスを指している。「Task」クラスの中の@Table(name = "tasks")でMySQLにありますtasksテーブルと紐づけを行っている
       そのテーブルと紐づけたTaskクラスを複数格納するためのリスト型のオブジェクト(物)をList<Task> tasksで作成しようとしていて
       その名称が「tasks」、Taskクラスを格納するリストを作成したということ
       Taskクラスに用意しておいたgetAllTasklistという動作をした結果できたリストをここで格納する */

        em.close();

        request.setAttribute("tasks", tasks);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);
    }
}
