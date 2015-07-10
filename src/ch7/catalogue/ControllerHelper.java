package ch7.catalogue;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.ButtonMethod;
import shared.HelperBaseCh5;
import shared.HibernateHelper;
import shared.SessionData;

public class ControllerHelper extends HelperBaseCh5 {
	private static final String JSP_BASE = "/WEB-INF/ch7/catalogue/";

	protected CatalogueItem item = new CatalogueItem();
	protected ShoppingCart<CatalogueItem> cart = new ShoppingCart<CatalogueItem>();

	public ControllerHelper(HttpServlet servlet, HttpServletRequest request,
			HttpServletResponse response) {
		super(servlet, request, response);
	}
	
	public Object getItem() {
	    return item;
	}
	
	public Object getCart() {
	    return cart;
	}
	
	static public void initHibernate(HttpServlet servlet) {
		HibernateHelper.initSessionFactory(CatalogueItem.class);
	}

	@Override
	protected void copyFromSession(Object sessionHelper) {
		if (sessionHelper.getClass() == this.getClass()) {
		    item = ((ControllerHelper) sessionHelper).item;
		    cart = ((ControllerHelper) sessionHelper).cart;
		}
	}
	
	protected String jspLocation(String page) {
        return JSP_BASE + page;
    }
	
	@ButtonMethod(isDefault=true)
	public String methodDefault() {
	    List<?> list = HibernateHelper.getListData(CatalogueItem.class);
	    request.setAttribute("allItems", list);
	    return jspLocation("BrowseLoop.jsp");
	}
	
	@ButtonMethod(buttonName = "addCart")
    public String methodAddCart() {
	    cart.addItem(item);
	    item = new CatalogueItem();
        return methodDefault();
    }
	
	@ButtonMethod(buttonName = "emptyCart")
	public String methodEmptyCart() {
	    cart.resetItems();
	    return methodDefault();
	}
	
	@ButtonMethod(buttonName = "viewItem")
	public String methodViewItem() {
	    fillBeanFromRequest(item);
	    if (item.getItemId() != null) {
            Object dbObj = HibernateHelper.getFirstmatch(item.getClass(),
                    "itemId", item.getItemId());
            if (dbObj != null) {
                item = (CatalogueItem) dbObj;
            }
	    }
	    return methodDefault();
	}
	
	@ButtonMethod(buttonName = "viewCart")
    public String methodViewCart() {
        return jspLocation("Cart.jsp");
    }
	
	@ButtonMethod(buttonName = "processCart")
	public String methodProcess() {
	    cart.setTotal(0);
	    cart.setCount(0);
	    for (CatalogueItem anItem : cart.getItems()) {
	        cart.addTotal(anItem.getPrice());
	        cart.incrCount();
	    }
	    return jspLocation("Process.jsp");
	}
	
	@Override
    public void doGet()
            throws ServletException, java.io.IOException 
    {
        //Call the method with false to place a new helper in the session
        addHelperToSession("helper", SessionData.IGNORE);

        String address = methodDefault();

        request.getRequestDispatcher(address).forward(request, response);
    }
	
	@Override
    public void doPost()
            throws ServletException, java.io.IOException 
    {
        addHelperToSession("helper", SessionData.READ);

        String address = executeButtonMethod();

        request.getRequestDispatcher(address).forward(request, response);
    }
}
