package dev.milca.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.milca.mvc.model.EmotionEnum;
import dev.milca.mvc.model.Moment;
import dev.milca.mvc.model.MomentTypeEnum;

class MomentRepositoryTest {
    private MomentRepository momentRepository;

    @BeforeEach
    void setUp() {
        momentRepository = new MomentRepository();
    }

    @Test
    void testAddMomentAndCheckSize() {
        Moment moment = new Moment(0, "Título de Test", "Descripción de Test", EmotionEnum.ALEGRIA, MomentTypeEnum.BUENO, LocalDate.now());

        momentRepository.addMoment(moment);

        assertEquals(1, momentRepository.getMoments().size(), "La lista debería tener 1 momento.");

        assertEquals(1, moment.getId(), "El ID del momento debería ser 1.");
    }


    @Test
    void testDeleteMomentById() {
        Moment moment = new Moment(0, "Para eliminar", "Momento a ser eliminado", EmotionEnum.TRISTEZA, MomentTypeEnum.MALO, LocalDate.now());
        momentRepository.addMoment(moment);
        
        assertEquals(1, momentRepository.getMoments().size(), "La lista debería tener un momento para eliminar.");
        
        boolean wasDeleted = momentRepository.deleteMomentById(moment.getId());
        
        assertTrue(wasDeleted, "El método debería retornar true si la eliminación fue exitosa.");
        assertTrue(momentRepository.getMoments().isEmpty(), "La lista de momentos debería estar vacía.");
    }
    
    
    @Test
    void testDeleteNonExistentMoment() {
        boolean wasDeleted = momentRepository.deleteMomentById(99);
        
        assertFalse(wasDeleted, "El método debería retornar false si el momento no existe.");
    }

    
    @Test
    void testFilterMomentsByEmotion() {
        momentRepository.addMoment(new Moment(0, "Día Feliz", "Un momento muy divertido", EmotionEnum.ALEGRIA, MomentTypeEnum.BUENO, LocalDate.now()));

        momentRepository.addMoment(new Moment(0, "Noticia triste", "Me siento triste porque...", EmotionEnum.TRISTEZA, MomentTypeEnum.MALO, LocalDate.now()));
        
        momentRepository.addMoment(new Moment(0, "Otro día feliz", "Salida familiar al parque", EmotionEnum.ALEGRIA, MomentTypeEnum.BUENO, LocalDate.now()));
        
        List<Moment> filteredMoments = momentRepository.filterMomentsByEmotion(EmotionEnum.ALEGRIA.name());
        
        assertEquals(2, filteredMoments.size(), "La lista filtrada debería tener 2 momentos.");
        
        for (Moment moment : filteredMoments) {
            assertEquals(EmotionEnum.ALEGRIA, moment.getEmotion(), "Todos los momentos filtrados deben ser de la emoción ALEGRIA.");
        }
    }
}

