<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <form method="post" 
  action="xsltcontroller?action=save">
 

    <table align="left" width="100%">
    	<tr>
        <td><b>Category</b></td>
        <td ><input type="text" name="category" value="${productCategory}"/>
       </td>
        
        </tr>
        
        <tr>
        <td><b>Subcategory</b></td>
        <td ><input type="text" name="subcategory" value="${productSubcategory}"/>
        </td>
        </tr>
        
        <tr>
        <td><b>Producer</b></td>
        <td ><input type="text" name="producer" /></td>
        
        </tr>
        
        <tr>
        <td><b>Issue date</b><br/>
              </td>
        <td ><input type="text" name="issueDate" /></td>
       
        </tr>
        
        <tr>
        <td><b>Model</b></td>
        <td ><input type="text" name="model" /></td>
        
        </tr>
        <tr>
        <td><b>Color</b></td>
        <td ><input type="text" name="color" /></td>
        </tr>
        
        
        <tr>
        <td><b>Price</b></td>
        <td ><input type="text" name="price" /></td>
        </tr>
        
        <tr>
        <td><b>Not in stock?</b></td>
        <td ><input type="checkbox" name="notInStock" /></td>
        </tr>
        <tr>
        
        <td  align="left">
        
        <button name="subject" type="submit" >Save</button>
          </form>
          <form method="post" action=
       "xsltcontroller?action=back&amp;page=goods&amp;productCategory=${productCategory}&amp;productSubcategory=${productSubcategory}">
  <button name="subject" type="submit">Back</button>
  </form>
       </td>
       <td  align="left">
     
       
       
        
</td>
    </tr>
    </table>   




