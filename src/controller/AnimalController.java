package controller;

import model.Animal;
import model.dao.AnimalDAO;

public class AnimalController {
    private AnimalDAO animalDAO;

    public AnimalController() {
        this.animalDAO = new AnimalDAO();
    }

    public void adicionarAnimal(Animal animal) {
        animalDAO.adiciona(animal);
    }

    public Animal buscarAnimalPorId(int id) {
        return animalDAO.buscarPorId(id);
    }

    public void atualizarAnimal(Animal animal) {
        animalDAO.atualizar(animal);
    }

    public void deletarAnimal(int id) {
        animalDAO.deletar(id);
    }

}
