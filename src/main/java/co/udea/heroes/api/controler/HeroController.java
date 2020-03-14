package co.udea.heroes.api.controler;

import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {
    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHero(@PathVariable int id){
        log.info("REST request buscar heroe");
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping
    @ApiOperation(value = "Buscar todos los heroes", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> getHeroes(){
        log.info("REST request buscar todos los heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }

    @GetMapping("/")
    @ApiOperation(value = "Buscar heroe que coincidan con el parámetro", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> searchHeroes(String name) {
        log.info("REST request buscar heroe que coincidan con el parámetro");
        return ResponseEntity.ok(heroService.searchHeroes(name));
    }

    @PostMapping
    @ApiOperation(value = "Añadir heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe añadido", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        log.info("REST request añadir heroe");
        return ResponseEntity.ok(heroService.addHero(hero));
    }

    @PutMapping
    @ApiOperation(value = "Actualizar heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe actualizado", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero){
        log.info("REST request actualizar heroe");
        return ResponseEntity.ok(heroService.updateHero(hero));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Borrar heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe borrado", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> deleteHero(@PathVariable int id){
        log.info("REST request borrar heroe");
        return ResponseEntity.ok(heroService.deleteHero(id));
    }

}
