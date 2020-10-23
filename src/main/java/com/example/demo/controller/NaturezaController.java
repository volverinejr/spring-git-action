package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ParamsRequestModel;
import com.example.demo.model.dto.DozerConverter;
import com.example.demo.model.dto.NaturezaDTO;
import com.example.demo.model.entity.Natureza;
import com.example.demo.model.service.INaturezaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(tags = { "NaturezaEndpoint" })
@SwaggerDefinition(tags = { @Tag(name = "NaturezaEndpoint", description = "descrição do endpoint") })
@RestController
@RequestMapping("/naturezas")
public class NaturezaController {

	@Autowired
	private INaturezaService _naturezaService;

	@Autowired
	private PagedResourcesAssembler<NaturezaDTO> assembler;

	private NaturezaDTO conerterToNaturezaDTO(Natureza natureza) {
		return DozerConverter.parseObject(natureza, NaturezaDTO.class);
	}

	@ApiOperation(value = "Localizar uma natureza específica")
	@GetMapping("/{id}")
	public NaturezaDTO FindById(@PathVariable Long id) {
		Natureza natureza = _naturezaService.FindById(id);
		NaturezaDTO naturezaDTO = this.conerterToNaturezaDTO(natureza);

		naturezaDTO.add(linkTo(methodOn(NaturezaController.class).FindById(id)).withSelfRel());

		return naturezaDTO;
	}

	@ApiOperation(value = "Listar todas as naturezas")
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<Natureza> naturezaPage = _naturezaService.findAll(prm);

		Page<NaturezaDTO> naturezaDTO = naturezaPage.map(this::conerterToNaturezaDTO);

		naturezaDTO.stream()
				.forEach(p -> p.add(linkTo(methodOn(NaturezaController.class).FindById(p.getKey())).withSelfRel()));

		PagedModel<?> resources = assembler.toModel(naturezaDTO);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "buscar natureza pelo nome")
	@GetMapping("/findByNome/{nome}")
	public ResponseEntity<?> findByNomeContaining(@PathVariable String nome, @RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<Natureza> naturezaPage = _naturezaService.findByNomeContaining(nome, prm);

		Page<NaturezaDTO> naturezaDTO = naturezaPage.map(this::conerterToNaturezaDTO);

		naturezaDTO.stream()
				.forEach(p -> p.add(linkTo(methodOn(NaturezaController.class).FindById(p.getKey())).withSelfRel()));

		PagedModel<?> resources = assembler.toModel(naturezaDTO);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "Salvar uma natureza")
	@PostMapping
	public NaturezaDTO save(@RequestBody @Valid NaturezaDTO naturezaDTO) {
		var entity = DozerConverter.parseObject(naturezaDTO, Natureza.class);

		Natureza natureza = _naturezaService.save(entity);

		naturezaDTO = this.conerterToNaturezaDTO(natureza);

		naturezaDTO.add(linkTo(methodOn(NaturezaController.class).FindById(natureza.getId())).withSelfRel());

		return naturezaDTO;
	}

	@ApiOperation(value = "Atualizar uma natureza")
	@PutMapping("/{id}")
	public NaturezaDTO update(@PathVariable Long id, @RequestBody @Valid NaturezaDTO naturezaDTO) {
		Natureza naturezaExiste = _naturezaService.FindById(id);

		naturezaExiste.Atualizar(naturezaDTO.getNome());

		Natureza natureza = _naturezaService.save(naturezaExiste);

		naturezaDTO = this.conerterToNaturezaDTO(natureza);

		naturezaDTO.add(linkTo(methodOn(NaturezaController.class).FindById(natureza.getId())).withSelfRel());

		return naturezaDTO;
	}

	@ApiOperation(value = "Remover uma natureza")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		_naturezaService.FindById(id);

		_naturezaService.deleteById(id);

		return ResponseEntity.ok().build();
	}

}
