package com.example.bookcatalog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private List<Book> books;

    @Override
    public void init() throws ServletException {
        books = new ArrayList<>();
        books.add(new Book("Основи програмування", "Джон Доу"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Каталог книг</title></head>");
        out.println("<body>");
        out.println("<h1>Каталог книг (Варіант 8)</h1>");

        out.println("<form method='post' action='books'>");
        out.println("Назва: <input type='text' name='title' required> ");
        out.println("Автор: <input type='text' name='author' required> ");
        out.println("<input type='submit' value='Додати книгу'>");
        out.println("</form>");

        out.println("<ul>");
        for (Book book : books) {
            out.println("<li><b>" + book.getTitle() + "</b> — " + book.getAuthor() + "</li>");
        }
        out.println("</ul>");

        out.println("<br><a href='index.jsp'>На головну</a>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String author = request.getParameter("author");

        if (title != null && !title.trim().isEmpty() && author != null && !author.trim().isEmpty()) {
            books.add(new Book(title, author));
        }
        response.sendRedirect("books");
    }
}
