package ch7.catalogue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.HibernateHelper;

@WebServlet("/ch7/catalogue/CreateCatalogue")
public class CreateCatalogue extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        HibernateHelper.createTable(CatalogueItem.class);
        HibernateHelper.initSessionFactory(CatalogueItem.class);
    }

    static final List<Object> itemList = new ArrayList<Object>();

    static {
        itemList.add(new CatalogueItem("A1", "The Foundation Trilogy",
                "A very fine book. Why not buy one?", 1.11));
        itemList.add(new CatalogueItem("T2", "The Hobbit",
                "A very fine book. Why not buy two?", 2.22));
        itemList.add(new CatalogueItem("Y3", "Light on Yoga",
                "A very fine book. Why not buy three?", 3.33));
        itemList.add(new CatalogueItem("M4", "Blue Monkey Sideshow",
                "A very fine book. Why not buy four?", 4.44));
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HibernateHelper.updateDB(itemList);
        response.getWriter().print("Catalogue Created");
        destroy();
    }

}
