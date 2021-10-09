# votingLunch

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users Admin can input a restaurant and it's lunch menu of the day (2-5 items
usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at Only one vote counted per user If user votes again the
same day:
If it is before 11:00 we assume that he changed his mind. If it is after 11:00 then it is too late, vote can't be
changed Each restaurant provides a new menu each day.

### curl samples (application deployed at application context `votinglunch`).

#### get All Users

'curl -s http://localhost:8080/votinglunch/rest/admin/users --user admin@gmail.com:admin'

#### get Users 100001

'curl -s http://localhost:8080/votinglunch/rest/admin/users/100001 --user admin@gmail.com:admin'

#### get Admin profile with votes

'curl -s http://localhost:8080/votinglunch/rest/admin/users/100001/with-votes --user admin@gmail.com:admin'

#### get Profile

'curl -s http://localhost:8080/votinglunch/rest/profile --user user@yandex.ru:password'

#### get Profile with votes

'curl -s http://localhost:8080/votinglunch/rest/profile/with-votes --user user@yandex.ru:password'

#### get all restaurants Admin

'curl -s http://localhost:8080/votinglunch/rest/admin/restaurants --user admin@gmail.com:admin'

#### get restaurant Admin

'curl -s http://localhost:8080/votinglunch/rest/admin/restaurants/100004 --user admin@gmail.com:admin'

#### get restaurant with dishes Admin

'curl -s http://localhost:8080/votinglunch/rest/admin/restaurants/100004/with-dishes --user admin@gmail.com:admin'

#### delete restaurant Admin

'curl -s -X DELETE http://localhost:8080/votinglunch/rest/admin/restaurants/100004 --user admin@gmail.com:admin'

#### create restaurant Admin

'curl -s -X POST -d '{"id":null,"name":"NewRestaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votinglunch/rest/admin/restaurants --user admin@gmail.com:admin'

#### update restaurant Admin

'curl -s -X PUT -d '{"id":100003,"name":"UpdateRestaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votinglunch/rest/admin/restaurants/100003 --user admin@gmail.com:admin'

#### get all restaurants Profile

'curl -s http://localhost:8080/votinglunch/rest/restaurants --user user@yandex.ru:password'

#### get restaurant Profile

'curl -s http://localhost:8080/votinglunch/rest/restaurants/100005 --user user@yandex.ru:password'

#### get restaurant not found

'curl -s -v http://localhost:8080/topjava/rest/restaurants/100009 --user user@yandex.ru:password'

#### get dishes today of restaurant 100005

'curl -s http://localhost:8080/votinglunch/rest/dishes/100005/today --user user@yandex.ru:password'

#### get dish of 100021 of restaurant 100005

'curl -s http://localhost:8080/votinglunch/rest/dishes/100005/100021 --user user@yandex.ru:password'

#### get filter dishes of restaurant 100005 of 2021-10-03 09:00:00

'curl -s http://localhost:8080/votinglunch/rest/dishes/100005/filter?date=2021-10-03 --user user@yandex.ru:password'

#### get dishes not found

'curl -s http://localhost:8080/votinglunch/rest/dishes/100005/10002 --user user@yandex.ru:password'

#### create dish Admin

'curl -s -X POST -d '{"id":null, "name":"NewDish", "price":34, "date":"2021-10-03"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votinglunch/rest/admin/dishes/100005 --user admin@gmail.com:admin'

#### update dish Admin

'curl -s -X PUT -d '{"id":100021,"name":"UpdateDish", "price":134, "date":"2021-10-03"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votinglunch/rest/admin/dishes/100005/100021 --user admin@gmail.com:admin'

#### delete dish Admin

'curl -s -X DELETE http://localhost:8080/votinglunch/rest/admin/dishes/100005/100022 --user admin@gmail.com:admin'

#### get all dishes of restaurant 100005

'curl -s http://localhost:8080/votinglunch/rest/dishes/100005/today --user user@yandex.ru:password'

#### get votes

'curl -s http://localhost:8080/votinglunch/rest/votes --user user@yandex.ru:password'

#### get vote

'curl -s http://localhost:8080/votinglunch/rest/votes/100031 --user user@yandex.ru:password'

#### get vote today

'curl -s http://localhost:8080/votinglunch/rest/votes/today --user user@yandex.ru:password'

#### create vote

'curl -s -X POST -d '{"id":null}' -H "Content-Type:application/json;charset=UTF-8" http://localhost:8080/votinglunch/rest/votes/restaurants/100004 --user user2@yandex.ru:password'

#### update vote

'curl -s -X PUT -d '{"id":"100031"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votinglunch/rest/votes/100031/100005 --user user@yandex.ru:password'

#### delete vote

'curl -s -X DELETE http://localhost:8080/votinglunch/rest/votes/100031 --user user@yandex.ru:password'
