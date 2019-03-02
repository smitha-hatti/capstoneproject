# capstoneproject: Online shopping application

Description:
This is a web based application project is intended for online shopping. The main objective here is to make it interactive for the users and be able to select clothing from wide range of categories. The user can view the complete specification of each item. Then can add items to the shopping cart and make payments in a very secure mode.


Tech Stacks:
Front end: Thymeleaf, Bootstrap
Database: MySQL
Backend: Java, Spring boot, Spring security, Spring MVC

Modules:

User Login or Registration Module: This module starts when the customer visits the homepage. Here the customer authentication and authorization takes place. If the user is already registered then he will be prompted to enter his login credentials. But if user is visiting the website for the first time then he may have to register and enter his details. Once the user is authenticated, he can visit the Shop All tab on the navbar menu and look at all the items. If an existing user forgets his password then he can recover his password by providing his login id through forgot password option and an email with new password will be sent to user’s registered email address.

Item Module: The user can browse through all the items in one page where all the items are displayed in a thumbnail format; he can look through various categories like Men’s, Women’s, Kids and Accessories. Just clicking on the image can see a detailed view of the item and the user can see an enlarged image of item with description, specifications, available sizes and price. The user can select size and quantity needed and the add items to the cart.

Shopping Cart & Checkout Modules: This module begins when the user adds the items to cart for checkout. The Cart table contains the item name, price, quantity and size added. The user can update cart if he decides to delete items from cart. Then when he clicks on checkout he will be asked to enter his payment details and address to be shipped. On successful checkout, cart will be flushed out and an order confirmation email will be sent to user’s registered email address.
