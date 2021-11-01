package br.com.nicolay.empresa.boot.web.controller;

import br.com.nicolay.empresa.boot.domain.Cargo;
import br.com.nicolay.empresa.boot.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/cargos")
public class CargoController {

    @Autowired
    private CargoService service;

    @RequestMapping(method  = RequestMethod.GET, value = "/cadastrar")
    public String cadastrar(Cargo cargo) {
        return "/cargo/cadastro.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listar")
    public String listar(ModelMap model) {
        model.addAttribute("cargos", service.buscarTodos());

        return "/cargo/lista.html";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/salvar")
    public String salvar(Cargo cargo) {
        service.salvar(cargo);
        return "redirect:/cargo/cadastrar";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("cargo", service.buscarPorId(id));
        return "/cargo/cadastro";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editar(Cargo cargo) {
        service.editar(cargo);
        return "redirect:/cargo/cadastrar";
    }

    public  String excluir(@PathVariable("id") Long id, ModelMap model) {
        if (!service.cargosTemFuncionarios(id)) {
            service.excluir(id);
        }
        return listar(model);
    }

}
