#Instruction For Running The Program
* Run the Spring Boot Application.
* Use PostMan to Send Request
* Send the Header X-Client-Id with unique client Id to Simulate different Clients

![Screenshot (68)](https://github.com/user-attachments/assets/73ab7dde-8c5b-42b9-a6b4-776ef3699772)

The Algorithm Used for Implementing RateLimiter is Leaky Bucket Algorithm and we are using Token Bucket Algorithm because it is simple to use and doesn't consume much of the resources
it will have fixed number of tokens per unit time if the token is persent in bucket it will accept the Request and if the bucket is empty before refill time it will reject the Request.
