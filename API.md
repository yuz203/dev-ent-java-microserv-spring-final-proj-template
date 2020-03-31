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
