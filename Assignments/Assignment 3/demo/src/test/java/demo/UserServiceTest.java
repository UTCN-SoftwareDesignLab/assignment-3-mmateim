package demo;

import demo.entity.User;
import demo.repository.UserRepository;
import demo.service.UserService;
import demo.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest {

    UserService userService;
    @Mock
    UserRepository userRepository;

    @Before
    public void setup() {
        userService = new UserServiceImpl(userRepository);
        List<User> users = new ArrayList<User>();
        User user = new User("admin", "admin", "ADMIN", "adminName");
        users.add(user);
        Mockito.when(userRepository.findByUsername("admin")).thenReturn(user);
        /*Mockito.when(userRepository.findByGenre("fantasy")).thenReturn(users);
        Mockito.when(userRepository.findByGenreAndName("fantasy", "Harry Potter")).thenReturn(users);*/
    }

    @Test
    public void findByUsername() {
        User user = userService.findByUsername("admin");
        Assert.assertTrue(user.getName().equals("adminName"));
    }
}
