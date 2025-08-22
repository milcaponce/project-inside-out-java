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
        momentRepository.addMoment(new Moment("Buen día", "Un agradable día en el parque", EmotionEnum.ALEGRIA, MomentTypeEnum.BUENO, LocalDate.of(2023, 10, 26)));
        momentRepository.addMoment(new Moment("Malas noticias", "Me contaron algo triste", EmotionEnum.TRISTEZA, MomentTypeEnum.MALO, LocalDate.of(2023, 10, 27)));
        momentRepository.addMoment(new Moment("Un delicioso almuerzo", "Hoy almorcé en casa junto a mis amigos", EmotionEnum.ALEGRIA, MomentTypeEnum.BUENO, LocalDate.of(2023, 10, 28)));
    }

    @Test
    void testAddMomentAndCheckSize() {
        Moment moment = new Moment(0, "Título de Test", "Descripción de Test", EmotionEnum.ALEGRIA, MomentTypeEnum.BUENO, LocalDate.now());

        momentRepository.addMoment(moment);

        assertEquals(4, momentRepository.getMoments().size(), "La lista debería tener 4 momentos después de agregar uno nuevo.");
    }


    @Test
    void testDeleteMomentById() {
        Moment moment = new Moment(0, "Para eliminar", "Momento a ser eliminado", EmotionEnum.TRISTEZA, MomentTypeEnum.MALO, LocalDate.now());
        momentRepository.addMoment(moment);
        
        assertEquals(4, momentRepository.getMoments().size(), "La lista debería tener 4 momentos antes de la eliminación.");
        
        boolean wasDeleted = momentRepository.deleteMomentById(moment.getId());
        
        assertTrue(wasDeleted, "El método debería retornar true si la eliminación fue exitosa.");
        assertEquals(3, momentRepository.getMoments().size(), "La lista de momentos debería tener 3 momentos después de la eliminación.");
    }
    
    
    @Test
    void testDeleteNonExistentMoment() {
        boolean wasDeleted = momentRepository.deleteMomentById(99);
        
        assertFalse(wasDeleted, "El método debería retornar false si el momento no existe.");
    }

    
    @Test
    void testFilterMomentsByEmotion() {
        List<Moment> filteredMoments = momentRepository.filterMomentsByEmotion(EmotionEnum.ALEGRIA.name());
        
        assertEquals(2, filteredMoments.size(), "La lista filtrada debería tener 2 momentos.");
        
        for (Moment moment : filteredMoments) {
            assertEquals(EmotionEnum.ALEGRIA, moment.getEmotion(), "Todos los momentos filtrados deben ser de la emoción ALEGRIA.");
        }
    }

    @Test
    void testFilterMomentsByType() {
        List<Moment> goodMoments = momentRepository.filterMomentsByType(MomentTypeEnum.BUENO);
        assertEquals(2, goodMoments.size(), "Debería encontrar 2 momentos de tipo BUENO.");
        assertEquals("Buen día", goodMoments.get(0).getTitle());
        assertEquals("Un delicioso almuerzo", goodMoments.get(1).getTitle());

        
        List<Moment> badMoments = momentRepository.filterMomentsByType(MomentTypeEnum.MALO);
        assertEquals(1, badMoments.size(), "Debería encontrar 1 momento de tipo MALO.");
        assertEquals("Malas noticias", badMoments.get(0).getTitle());
    }
}

