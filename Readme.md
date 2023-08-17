# One Box Cart API

## Getting Started

### Prerequisites

- ##### Docker installed:

Check [Docker](https://docs.docker.com/get-docker/) for installation instructions

Verify installation by running `docker --version` in a terminal

## How it works?

The aim of this API is to provide a simple way to manage a shopping cart. It is a REST API that allows you to create a
cart, add products to it, remove products from it, etc.

### What were the design decisions?

* **A well-designed solution and architecture.** It was decided to use a **Clean Architecture** approach to achieve this
  goal. This approach
  allows us to create a highly scalable and maintainable code base. It also allows us to create a code base that is
  easy to test and extend.
* **Minimum test coverage.** It was created unit tests for the main classes and core business logic. I was decided to
  use **JUnit** and **Mockito** to achieve this goal.
* **Documentation**. It was created a **Swagger** documentation to describe the API and how to use it. And also it was
  added a Class Diagram to help understand the code structure in a quick glance.

### Business logic decisions

* **A cart MUST be deleted automatically due to 10 minutes of inactivity**: The cart is not physically deleted from the
  database, but it is marked as EXPIRED. This is to allow the user to recover the cart if he wants to.
* The design of the domain could include the credit card information, but it was decided to not include to reduce the
  complexity of the domain. The credit card information could be added in the future if needed. This type of information
  should be stored in a secure way, so it is not a good idea to store it in the database as plain text. It should be
  encrypted or hashed.
