Recipe Data Collection and API Development
Project Overview

The purpose of this project is to demonstrate my ability to work with structured data, store it in a database, and serve it through a RESTful API. I was given a JSON file containing recipe data and my goal was to parse it, design an appropriate database, and create endpoints to manage and query recipes.

While working on this project, I focused on data integrity, validation, and ensuring that the API is user-friendly and extensible for future enhancements.

Project Structure
## Project Structure

<img width="501" height="352" alt="image" src="https://github.com/user-attachments/assets/cd64a339-ab3d-48dd-bb34-8d3c67763066" />

**Thought Process and Approach**
Step 1: Understanding the JSON Data

I first examined the JSON structure carefully. Each recipe contained fields like title, cuisine, rating, prep_time, cook_time, description, nutrients, and serves.

I had to consider how to handle nested JSON for the nutrients field. My thought was to keep it as a JSON column in the database rather than breaking it into multiple columns. This approach provides flexibility, especially if future recipes have additional nutritional info.

While designing the database, my goal was to capture all the necessary fields while keeping it normalized and scalable.

id: Primary key for unique identification.

title, cuisine: Strings, mandatory fields.

rating: Float, optional as some recipes may not have ratings initially.

prep_time, cook_time, total_time: Integers for calculation and sorting purposes.

description: Text field to allow detailed descriptions.

nutrients: JSON column for flexible storage of calories, protein, carbs, fat.

serves: String to indicate number of servings.

**SQL Table Schema:**

CREATE TABLE recipes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    cuisine VARCHAR(255) NOT NULL,
    rating FLOAT,
    prep_time INT NOT NULL,
    cook_time INT NOT NULL,
    total_time INT NOT NULL,
    description TEXT,
    nutrients JSON,
    serves VARCHAR(50)
);
**Step 3: Parsing and Inserting Data**

I used the Jackson library in Java for parsing the JSON. My focus was on robustness and error handling:

Checked for missing mandatory fields (title, cuisine, prep_time, cook_time) before inserting.

Converted nutrients into a JSON-compatible format for the database.

Calculated total_time before saving.

This approach ensures that any invalid data from the JSON file will be detected early and not corrupt the database.

S**tep 4: API Design**

I designed two main endpoints to showcase both data creation and querying with business logic.

**POST /recipes**

Purpose: Add a new recipe to the database.

Validation: Ensures mandatory fields are present.

Logic: Calculates total_time automatically.

Response: Returns the saved recipe including id and total_time.

**Screenshot:**
Add screenshot here showing a successful POST request response.
<img width="1029" height="814" alt="image" src="https://github.com/user-attachments/assets/6d76c232-339e-40b8-8737-ab9871f00fcd" />

GET /recipes/top
<img width="1424" height="969" alt="image" src="https://github.com/user-attachments/assets/0b44bf2f-b6d8-492b-9873-166130025cd7" />


Purpose: Retrieve top-rated recipes.

Query Parameter: Optional limit (default 5).

Logic: Queries database, sorts by rating descending, and limits results.

Response: Returns a JSON array under data key.

Screenshot Placeholder:
Add screenshot here showing a successful GET request response.
![GET /recipes/top output](screenshots/get_top_recipes.png)

**Step 5: Testing**

I manually tested both endpoints using Postman:

POST /recipes: Tested with valid data, missing mandatory fields, and invalid types.

GET /recipes/top: Tested default limit and custom limit parameter to ensure correct sorting and filtering.

**Step 6: Reflection and Next Steps**

Handling nested JSON in a relational database allows flexibility for future expansion of recipe fields.

Automatically calculating derived fields (total_time) ensures consistent data integrity.

The API is extendable: it can later support search by cuisine, filtering, pagination, and authentication.

**Running the Project**

**Clone the repository:**

git clone 
cd recipe-api

Create database and run schema.sql.

Configure application.properties with your MySQL credentials.

**Run the Spring Boot application:**

mvn spring-boot:run

Test the endpoints using Postman or cURL.
