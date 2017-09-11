# NoteShare
This is an Android App which is a platform for making notes or writing down important information that one may want to remember later on. The notes can also be shared between friends for a good laugh or for motivation. NoteShare will be a blend of social media and item tracking.

## Getting Started
For development and testing follow these steps:

1. `Git clone`
2. open folder with `Android Studio`

## Prerequisites
You will need to have installed:

* `java jdk` [follow instructions](http://www.wikihow.com/Install-Oracle-Java-on-Ubuntu-Linux)
* `android studio` [download here](https://developer.android.com/studio/index.html)
* knowledge in `java` also required

### Installing
* The installation of android studio can be found [here](https://developer.android.com/studio/index.html). Follow the steps until android studio is fully installed.
* Full install instructions for java jdk can be found [here](http://www.wikihow.com/Install-Oracle-Java-on-Ubuntu-Linux). This is for linux based systems. For other operating system, chose the system in the options before downloading the jdk.

## Running Tests
To run test during development, three test dependencies should be added to the build.gradle(Module:app):
* `junit` for unit testing in java
* `robolectric` for unit testing specific to android apps
* `espresso` for instrumentation testing

### Instrumentation testing
This is important in order to test how well the app will work. This testing emulates an android for or uses an android phone with developer options activated.
### Unit testing
This is important in order to test the logic used in the app. This tests the backend bit of the app.

## Deployment
Deployment can be done to Google Play Store or use appetize.io(for streaming Android Natice Apps in the Browser)

## Built With
* `java` - The backend language
* `android studio` - The IDE for android development

### Author
* Albert Oketch
Credit [Moringa School](http://moringaschool.com/)
