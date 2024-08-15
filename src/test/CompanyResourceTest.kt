package edu.uchicago.gerber.quark

import edu.uchicago.gerber.quark.models.Company
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class CompanyResourceTest {

    // CREATE
    @Test
    fun testCreateCompany() {
        val newCompany = Company(name = "New Company", industry = "Tech")

        given()
            .contentType("application/json")
            .body(newCompany)
            .`when`().post("/companies")
            .then()
            .statusCode(201)
            .body("name", `is`("New Company"))
    }

    // READ
    @Test
    fun testGetCompany() {
        given()
            .`when`().get("/companies/{id}", "12345")
            .then()
            .statusCode(200)
            .body("id", `is`("12345"))
    }

    @Test
    fun testGetCompanies() {
        given()
            .`when`().get("/companies")
            .then()
            .statusCode(200)
    }

    @Test
    fun testGetPagedCompanies() {
        given()
            .`when`().get("/companies/paged/{page}", 1)
            .then()
            .statusCode(200)
    }

    // UPDATE
    @Test
    fun testUpdateCompany() {
        val updatedCompany = Company(id = "12345", name = "Updated Company", industry = "Finance")

        given()
            .contentType("application/json")
            .body(updatedCompany)
            .`when`().put("/companies")
            .then()
            .statusCode(200)
            .body("name", `is`("Updated Company"))
    }

    // DELETE
    @Test
    fun testDeleteCompany() {
        given()
            .`when`().delete("/companies/{id}", "12345")
            .then()
            .statusCode(204)
    }

    @Test
    fun testDeleteAllCompanies() {
        given()
            .`when`().delete("/companies")
            .then()
            .statusCode(204)
    }
}
