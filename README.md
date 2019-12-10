# LoginApp
Credit for most of the application goes to Ankit Wasankar's tutorial series (https://www.youtube.com/playlist?list=PL3hpmQhMoz-cz1GBAtovJyrfspZctG03L, videos 1-4). Any of my own additions to the code are noted in their respective files.

This application is made in Java, and was developed using Spring Boot. Functions include:
-Registering a new user into a MySQL database using First and Last names, email, password, security question, and security answer.
-Logging in with a valid email and password, bringing users to a simple HTML page
-Links to navigate between pages

In addition to Wasankar's code, I attempted to set up a password recovery system which uses the security question and answer a user provided during registration. A password recovery page is accessible, but isn't fully functional. This framework relies on a couple foreign key constraints during the authentication process and doesn't provide access to them in the code; because of this, I couldn't find a way to modify existing user data without refactoring a large part of the code. With more time, I would either re-work the authentication process or find a way to access and modify the foreign keys in code.
