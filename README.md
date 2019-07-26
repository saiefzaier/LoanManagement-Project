This project consists of a loan management system.<br><br>

NB: banks<br>
Attijari: username:attijari password:attijari<br>
UBCI: ubci:attijari password:ubcihow <br>
Biat: username:attijari password:attijari<br>




Upon login the program will check if the user is a customer or a bank administrator and will display the appropriate frame<br><br>
A new customer can sign up but banks are predefined <br><br>
When you click on next<br><br>
If cin already exists or  is not a number or is null or its size is different than 8 then the appropriate message will be displayed<br><br>
If first name or lasname are null a message is displayed<br><br>
If all field are correct The user is passed to the next panel<br><br>
If the username already exists or is null the appropriate message will be displayed.<br><br>
Is the password is null a message will be displayed<br><br>
Any user can change its credentials (Verified in class).<br><br>
Upon login a customer can request a loan, values of loan after interest monthly payment change dynamically within the panel, the customer can also view his pending loans (Verified in class).
 and accepted loans<br><br>
Upon login a bank administrator can see the number of pending loans on the top right of the corner it varies when the bank admin accepts or refuses a loan. 
The bank admin can accept/refuse a pending loan (Values are taken straight from the JTable based on selected row)
or view the registered loans at his bank. <br><br>












Database diagram It uses a remote mysql database (remotemysql.com) 
![Imgur](https://i.imgur.com/ZNphQJI.png)






Class diagram

![Imgur](https://i.imgur.com/swiMvbP.png)
