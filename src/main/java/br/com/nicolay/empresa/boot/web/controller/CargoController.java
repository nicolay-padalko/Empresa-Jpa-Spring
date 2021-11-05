package br.com.nicolay.empresa.boot.web.controller;

import br.com.nicolay.empresa.boot.domain.Cargo;
import br.com.nicolay.empresa.boot.domain.Departamento;
import br.com.nicolay.empresa.boot.service.CargoService;
import br.com.nicolay.empresa.boot.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(path = "/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private DepartamentoService departamentoService;

    @RequestMapping(method  = RequestMethod.GET, value = "/cadastrar")
    public String cadastrar(Cargo cargo) {
        return "/cargo/cadastro.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listar")
    public String listar(ModelMap model) {
        model.addAttribute("cargos", cargoService.buscarTodos());

        return "/cargo/lista.html";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/salvar")
    public String salvar(Cargo cargo, RedirectAttributes attr) {
        cargoService.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso");
        return "redirect:/cargos/cadastrar";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("cargo", cargoService.buscarPorId(id));
        return "/cargo/cadastro.html";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editar(Cargo cargo, RedirectAttributes attr) {
        cargoService.editar(cargo);
        attr.addFlashAttribute("success", "Registro atualizado com sucesso!");
        return "redirect:/cargos/cadastrar";
    }

    public  String excluir(@PathVariable("id") Long id, ModelMap model) {
        if (!cargoService.cargosTemFuncionarios(id)) {
            cargoService.excluir(id);
        }
        return listar(model);
    }

    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos() {
        return departamentoService.buscarTodos();
    }

}
