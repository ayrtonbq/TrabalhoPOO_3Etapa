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

    public Animal buscarAnimalPorId(int int1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarAnimalPorId'");
    }

    // Outros métodos de controle para leitura, atualização e deleção...
}
