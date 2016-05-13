web-letter
==========

Edit a simple web form to get a formatted pdf letter

## Running Locally

Make sure you have Java 8 and Maven 2 installed.  Also, install the [Heroku Toolbelt](https://toolbelt.heroku.com/).

```sh
$ mvn install
$ ./start-local-webapp.sh
```
Your app should now be running on [localhost:5000](http://localhost:5000/).

## Deploying to Heroku

```sh
$ git remote add heroku git@heroku.com:web-letter.git
$ git push heroku master
$ heroku open
```
