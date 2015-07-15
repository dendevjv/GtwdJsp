package ch7.catalogue.persistent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.HibernateHelper;

@WebServlet("/ch7/catalogue/persistent/CreateCatalogue")
public class CreateCataloguePersist extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        HibernateHelper.createTable(CatalogueItemPersist.class);
        HibernateHelper.initSessionFactory(CatalogueItemPersist.class);
    }

    static final List<Object> itemList = new ArrayList<Object>();

    static {
        itemList.add(new CatalogueItemPersist("A1", "The Foundation Trilogy",
                "A very fine book. Why not buy one?", 1.11));
        itemList.add(new CatalogueItemPersist("T2", "The Hobbit",
                "A very fine book. Why not buy two?", 2.22));
        itemList.add(new CatalogueItemPersist("Y3", "Light on Yoga",
                "A very fine book. Why not buy three?", 3.33));
        itemList.add(new CatalogueItemPersist("M4", "Blue Monkey Sideshow",
                "A very fine book. Why not buy four?", 4.44));
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HibernateHelper.updateListDB(itemList);
        response.getWriter().print("Persistent Catalogue Created");
        destroy();
    }

    public static void createCatalogue() {
        List<Object> itemsDB = (List<Object>) HibernateHelper
                .getListData(CatalogueItemPersist.class);
        for (Object item : itemsDB) {
            HibernateHelper.removeDB(item);
        }
        HibernateHelper.updateDB(itemList);
    }
}
