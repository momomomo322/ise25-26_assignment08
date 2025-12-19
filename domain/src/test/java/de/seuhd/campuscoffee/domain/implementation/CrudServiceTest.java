package de.seuhd.campuscoffee.domain.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import de.seuhd.campuscoffee.domain.model.objects.User;
import de.seuhd.campuscoffee.domain.ports.data.CrudDataService;
import de.seuhd.campuscoffee.domain.ports.data.UserDataService;
import de.seuhd.campuscoffee.domain.tests.TestFixtures;

@ExtendWith(MockitoExtension.class)
public class CrudServiceTest {

    @Mock
    private UserDataService userDataService;

    private CrudServiceImpl<User, Long> crudService;
    @BeforeEach
    void beforeEach() {
        crudService = new CrudServiceImpl<>(User.class) {
            @Override
            protected CrudDataService<User, Long> dataService() {
                return userDataService;
            }
        };
    }

    @Test
    void deleteSuccessfully() {
        // given
        User user = TestFixtures.getUserFixtures().getFirst();
        assertNotNull(user.getId());

        // when
        crudService.delete(user.getId());

        // then
        verify(userDataService).delete(user.getId());
    }

    @Test
    void clearSuccessfully() {
        // given
        User user = TestFixtures.getUserFixtures().getFirst();
        assertNotNull(user.getId());

        // when
        crudService.clear();

        // then
        verify(userDataService).clear();
    }
}