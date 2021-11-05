package br.com.nicolay.empresa.boot.web.controller;

import br.com.nicolay.empresa.boot.domain.Funcionario;
import br.com.nicolay.empresa.boot.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastrar")
    public String cadastrar(Funcionario funcionario) {
        return "funcionario/cadastro.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listar")
    public String listar(ModelMap model) {
        model.addAttribute("funcionarios", service.buscarTodos());
        return "/funcionario/lista.html";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/salvar")
    public String salvar(Funcionario funcionario) {
        service.salvar(funcionario);
        return "redirect:/funcioanrio/cadastro";
    }

    @RequestMapping(value = "/editar/{id}", method =RequestMethod.GET)
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("funcionario", service.buscarPorId(id));
        return "redirect:/funcionario/cadastro";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editar(Funcionario funcionario) {
        service.editar(funcionario);
        return "redirect:/funcionario/cadastro";
    }
}
