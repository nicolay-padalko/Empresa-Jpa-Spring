package br.com.nicolay.empresa.boot.service;

import br.com.nicolay.empresa.boot.dao.CargoDao;
import br.com.nicolay.empresa.boot.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = false)
public class CargoServiceImpl implements CargoService{

    @Autowired
    private CargoDao dao;


    @Transactional(readOnly = false)
    @Override
    public void salvar(Cargo cargo) {
        dao.save(cargo);
    }

    @Transactional(readOnly = false)
    @Override
    public void editar(Cargo cargo) {
        dao.update(cargo);
    }

    @Transactional(readOnly = false)
    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Cargo buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cargo> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public boolean cargosTemFuncionarios(Long id) {
        if (buscarPorId(id).getFuncionarios().isEmpty()) {
            return false;
        }
        return true;
    }



}
