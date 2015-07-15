package ch7.catalogue.persistent;

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
	private static final String JSP_BASE = "/WEB-INF/ch7/catalogue/persistent/";

	protected CatalogueItemPersist item = new CatalogueItemPersist();
	protected ShoppingCartPersist<CatalogueItemPersist> cart = new ShoppingCartPersist<CatalogueItemPersist>();

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
	    boolean create = Boolean.parseBoolean(servlet.getInitParameter("create"));
	    if (create) {
	        HibernateHelper.createTable(
	                ShoppingCartPersist.class,
	                CatalogueItemPersist.class);
	    }
		HibernateHelper.initSessionFactory(
		        ShoppingCartPersist.class,
		        CatalogueItemPersist.class);
		/*
		 * Commented out because of exception below:
		 * org.hibernate.StaleStateException: Batch update returned unexpected row count 
		 * from update [0]; actual row count: 0; expected: 1
		 */
		if (create) {
		    CreateCataloguePersist.createCatalogue();
		}
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
	
	public String methodBrowseLoop() {
	    List<?> list = HibernateHelper.getListData(CatalogueItemPersist.class);
	    request.setAttribute("allItems", list);
	    return jspLocation("BrowseLoop.jsp");
	}
	
	@ButtonMethod(buttonName="loginButton", isDefault=true)
	@SuppressWarnings("unchecked")
    public String loginMethod() {
	    String address;
	    fillBeanFromRequest(cart);
	    setErrors(cart);
	    if (isValidProperty("accountNumber")) {
	        Object dataPersistent = HibernateHelper.getFirstmatch(cart.getClass(), "accountNumber", cart.getAccountNumber());
	        if (dataPersistent != null) {
	            cart = (ShoppingCartPersist<CatalogueItemPersist>)dataPersistent;
	        }
	        clearErrors();
	        address = methodBrowseLoop();
	    } else {
	        address = jspLocation("Login.jsp");
	    }
	    return address;
	}
	
	@ButtonMethod(buttonName = "addCart")
    public String methodAddCart() {
	    cart.addItem(item);
	    item = new CatalogueItemPersist();
        return methodBrowseLoop();
    }
	
	@ButtonMethod(buttonName = "emptyCart")
	public String methodEmptyCart() {
	    cart.resetItems();
	    return methodBrowseLoop();
	}
	
	@ButtonMethod(buttonName = "viewItem")
	public String methodViewItem() {
	    fillBeanFromRequest(item);
	    if (item.getItemId() != null) {
            Object dbObj = HibernateHelper.getFirstmatch(item.getClass(),
                    "itemId", item.getItemId());
            if (dbObj != null) {
                item = (CatalogueItemPersist) dbObj;
            }
	    }
	    return methodBrowseLoop();
	}
	
	@ButtonMethod(buttonName = "viewCart")
    public String methodViewCart() {
        return jspLocation("Cart.jsp");
    }
	
	@ButtonMethod(buttonName = "processCart")
	public String methodProcess() {
	    cart.setTotal(0);
	    cart.setCount(0);
	    for (CatalogueItemPersist anItem : cart.getItems()) {
	        cart.addTotal(anItem.getPrice());
	        cart.incrCount();
	    }
	    return jspLocation("Process.jsp");
	}

	@ButtonMethod(buttonName="saveCart")
    public String saveCartMethod() {
        methodProcess();
        HibernateHelper.updateDB(cart);
        return jspLocation("SaveCart.jsp");
    }
	
	@ButtonMethod(buttonName = "newUser")
    public String newUserMethod() {
        return jspLocation("Login.jsp");
    }
	
	@Override
    public void doGet()
            throws ServletException, java.io.IOException 
    {
        addHelperToSession("helper", SessionData.IGNORE);

        String address = loginMethod();
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
