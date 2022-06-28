package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {
    
    // Final attributes for actions
    private final String regAction = "register";
    private final String addAction = "add";
    private final String delAction = "delete";
    private final String lgoAction = "logout";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Getting current session and user
        HttpSession currSession = request.getSession();
        String currUser = (String) currSession.getAttribute("username");
        
        //System.out.println("Do get: curr user - " + currUser);
        
        if(currUser != null) {
            // Checking the action parameter
            String actionParam = request.getParameter("action");
            
            if (actionParam !=null && actionParam.equals(lgoAction)) {
                currSession.invalidate();
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            }
            
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Getting current session and user
        HttpSession currSession = request.getSession();
        String currUser = (String) currSession.getAttribute("username");
        
        String action = request.getParameter("action");
        
        if(currUser == null && action !=null && action.equals(regAction)) {
            String newUser = request.getParameter("reg_username");
            
            if(newUser == null || newUser.trim().isEmpty()) {
                request.setAttribute("message", "Username can't be empty.");
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
            
            currSession.setAttribute("username", newUser);
            
            // Adding list         
            currSession.setAttribute("shoppingList", new ArrayList<String>());
        } else if(currUser != null) {
            
            if(action != null) {
                if(action.equals(addAction)) {
                    addItemToList(request);
                } else if (action.equals(delAction)) {
                    delItemFromList(request);
                }
            }
        }
        
        //Remove Comm: It will always forwart towards the shopping list jsp, if any condition occur, we use return
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }
    
    private void addItemToList(HttpServletRequest request) {        
        String newItem = request.getParameter("new_item");
        
        if (newItem == null || newItem.trim().isEmpty()) {
            request.setAttribute("message", "Item name can't be empty.");
            return;
        }
        
        ArrayList<String> currList = (ArrayList<String>) request.getSession().getAttribute("shoppingList");
        currList.add(newItem);
    }
    
    private void delItemFromList(HttpServletRequest request) {
        ArrayList<String> currList = (ArrayList<String>) request.getSession().getAttribute("shoppingList");
        
        int itemIndexToRem = Integer.parseInt(request.getParameter("selected_item"));
        
        if(currList.size() > 0 && itemIndexToRem >= 0) {
            currList.remove(itemIndexToRem);
        }
    }
}
