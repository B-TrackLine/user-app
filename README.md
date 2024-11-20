# About this repository
This is a mono repo containing a backend application based on Spring Boot 3 that provides an API to manage user entities
and an Angular 18 frontend application serving as an interface to this API.

### Prerequisites (running)
- Java 21 JDK installed
- Preferable run this project on linux / WSL. If running on Windows, use `gradlew.bat` instead of `./gradlew`

Notes:\
There is no need to install node, npm or Angular CLI when using this project as the gradle build automatically pulls the correct npm version and uses it to build and serve the frontend application.\
However, in order continue development in the repo, it is necessary to provide theses dependencies

### Running
- Start the backend service by running `./gradlew user-service:bootRun` from within the main directory `user-app`
- Serve the frontend application by running `./gradlew user-frontend:serve` from within the main directory `user-app`
- Open the frontend application: http://localhost:4200
- [Optional] Open the backend API definition: http://localhost:8080/api-doc/index.html

### Development
- You should have java v21, node v22 and Angular CLI installed and available
- You can start the backend service by running the gradle `bootRun` task
- You can start the frontend application from your favourite IDE by  running `ng serve` inside a terminal

### Updating the generated models
The frontend application relies on a generated API client and models to communicate with the backend service.
To update the generated files (e.g. after an API change), do the following:
- ensure that the development requirements are met
- start the backend service and download the API spec (swagger.json.yaml) from `http://localhost:8080/api-doc/swagger.json.yaml`
- copy the new API spec into the directory `user-app/user-frontend/api` and name it `userservice.yaml`, overwriting the existing file
- remove the contents of `user-app/user-frontend/src/generated/userservice`
- run the following command inside the `user-frontend` directory: `npx openapi-generator-cli generate`
- you might need to update some of the Angular code if the new API version contains breaking changes