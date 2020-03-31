**Show All Customers**
----
  Returns json data about all customers.

* **URL**

  /printAllCustomers 
  
* **Method:**

  `GET`
  
*  **URL Params**

  None 

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{user_id: 1, first_name:”Jack", last_name:"Smith", addr: "999 First St.", phone:null, email:"jacksmith@gmail.com", created_at:2020-03-31 00:01:32.0},
{user_id: 2, first_name:”Mary", last_name:"Smith", addr: "777 First St..", phone:null, email:"marysmith@gmail.com", created_at:2020-03-31 00:04:15.0}`

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "There is not any customer" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/printAllCustomers",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  
  
**Add Customer**
----
  Returns json data about whether add customer successfully or not.

* **URL**

  /addCustomer 
  
* **Method:**

  `POST`
  
*  **URL Params**

  None 

* **Data Params**

  firstName: required 
  lastName: required
  address: required
  email: required
  example : {"firstName": "Mary", "lastName": "Smith", "address": "777 First St.", "email": "marysmith@gmail.com"}

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `Rows updated: 1(base)`

* **Error Response:**

  * **Code:** 400 Invalid Request Exception <br />
    **Content:** `{ error : "Invalid request" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/addCustomer",
      dataType: "json",
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  
  
**Delete Customer**
----
  Returns json data about whether delete customer successfully or not.

* **URL**

  /deleteCustomer 
  
* **Method:**

  `POST`
  
*  **URL Params**

  None 

* **Data Params**

  firstName: required 
  lastName: required
  example : {"firstName": "Mary", "lastName": "Smith"}

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `Rows updated: 1(base)`

* **Error Response:**

  * **Code:** 400 Invalid Request Exception <br />
    **Content:** `{ error : "Invalid request" }`

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/deleteCustomer",
      dataType: "json",
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
  ```

