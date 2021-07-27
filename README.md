# JavaSpringBootJPA

List employees get api:
http://localhost:8080/

Create employee post api:
http://localhost:8080/create

body: { name: "", email: "", phone: "" }


Using this application we can create a employee and can send email to the created employee for this purpose we used rabbitmq.
We can also list the created employees.

Whenever we create a employee an entry is pushed to queue. Listener to that queue will send email for the created user.

When deploying in aws ec2 have not cretaed rabbitmq-server, so java application withh just throw and warning exception.
