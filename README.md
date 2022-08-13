# HEBImageAPI

Coding exercise completed for HEB interview.

Application written using Spring Boot. 

Image Detection is handled via [Google Vision](https://cloud.google.com/vision).

Images are persisted using a MySql database.

In order to make database and image detection API connections, you will need to provide an updated `application.properties` file.

## Overview

Build a HTTP REST API in {**Java|Node.js|Python**} for a service that ingests user images, analyzes them for object detection, and returns the
enhanced content. It should implement the following specification:

**API Specification**

GET /images
* Returns HTTP 200 OK with a JSON response containing all image metadata.

GET /images?objects="dog,cat"
* Returns a HTTP 200 OK with a JSON response body containing only images that have the detected objects specified in the query
parameter.

GET /images/{imageId}
* Returns HTTP 200 OK with a JSON response containing image metadata for the specified image.

POST /images
* Send a JSON request body including an image file or URL, an optional label for the image, and an optional field to enable object
detection.
* Returns a HTTP 200 OK with a JSON response body including the image data, its label (generate one if the user did not provide it), its
identifier provided by the persistent data store, and any objects detected (if object detection was enabled).

### Object detection instructions

Image object detection can be performed using any API offering of your choosing (such as [Google](https://cloud.google.com/vision), IBM, [Imagga](https://imagga.com/), etc), or with a process
managed by your backend. The only requirement is that it must return a list of object names detected within that image.

That is the extent of the API contract. HTTP error codes should be used to indicate the proper level of system failure (i.e. client versus server).

### Database

A persistent data store is required, any variant of SQL is encouraged.

### Expectations

No frontend is required, but you may create one to demo the API. Regardless, a user of the API should be able to:

* Upload an optionally labelled image and run image object detection on it
* Retrieve all images and any metadata obtained from their analyses
* Search for images based on detected objects
