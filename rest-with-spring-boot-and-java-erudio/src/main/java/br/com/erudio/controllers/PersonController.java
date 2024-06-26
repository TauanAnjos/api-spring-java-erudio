package br.com.erudio.controllers;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.services.BookService;
import br.com.erudio.services.PersonService;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
    @Autowired
    PersonService personService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value ="/{id}",produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a Person", description = "Finds a Person", tags = {"People"},responses = {
            @ApiResponse(description = "Sucess", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))

            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public PersonVO findById(@PathVariable(value = "id") Long id){
        return personService.findById(id);

    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all People", description = "Finds all People", tags = {"People"},responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema =  @Schema(implementation = PersonVO.class))
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public List<PersonVO> findAll(){
        return personService.findAll();

    }
    @CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br" })
    @PostMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a Person", description = "Adds a Person", tags = {"People"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))

            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public PersonVO create(@RequestBody PersonVO person){
        return personService.create(person);

    }
    /*@PostMapping(value = "/v2",produces =  {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a Person", description = "Adds a Person", tags = {"People"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))

            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
        return personService.createV2(person);

    }*/
    @PutMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a Person", description = "Updates a Person", tags = {"People"}, responses = {
            @ApiResponse(description = "Update", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))

            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public PersonVO update(@RequestBody PersonVO person){
        return personService.update(person);

    }
    @DeleteMapping(value ="/{id}")
    @Operation(summary = "Deletes a Person", description = "Deletes a Person", tags = {"People"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
