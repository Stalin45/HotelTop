package fortest;

import com.hoteltop.common.OrderService;
import com.hoteltop.common.RoomService;
import com.hoteltop.common.UserService;
import com.hoteltop.common.impl.OrderServiceImpl;
import com.hoteltop.common.impl.RoomServiceImpl;
import com.hoteltop.common.impl.UserServiceImpl;
import com.hoteltop.model.Order;
import com.hoteltop.model.Room;
import com.hoteltop.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Vlastelin on 11.04.2016.
 */
public class  Main {

    private static final OrderService orderService = new OrderServiceImpl();

    private static final UserService userService = new UserServiceImpl();

    private static final RoomService roomService = new RoomServiceImpl();

    public static void main(String[] args) {
        User user1 = new User("Ilia", "Kapralov");
        User user2 = new User("Vlad", "Ataev");
        User user3 = new User("Roman", "Timonin");
        User user4 = new User("Ilia", "Ivanov");
        User user5 = new User("Anton", "Vishnyakov");

        Room room1 = new Room((byte) 1, 100, "standart");
        Room room2 = new Room((byte) 2, 200, "standart");
        Room room3 = new Room((byte) 1, 200, "luxe");
        Room room4 = new Room((byte) 3, 400, "luxe");

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);
        userService.createUser(user4);
        userService.createUser(user5);

        roomService.createRoom(room1);
        roomService.createRoom(room2);
        roomService.createRoom(room3);
        roomService.createRoom(room4);

        Calendar date = Calendar.getInstance();

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(orderService.makeOrder(user1, room1, date.getTime(), (short) 1));
        orderList.add(orderService.makeOrder(user1, room2, date.getTime(), (short) 2));
        orderList.add(orderService.makeOrder(user1, room3, date.getTime(), (short) 3));

        orderList.add(orderService.makeOrder(user2, room1, date.getTime(), (short) 3));
        orderList.add(orderService.makeOrder(user2, room2, date.getTime(), (short) 2));
        orderList.add(orderService.makeOrder(user2, room3, date.getTime(), (short) 15));
        orderList.add(orderService.makeOrder(user2, room4, date.getTime(), (short) 0));

        orderList.add(orderService.makeOrder(user3, room1, date.getTime(), (short) -1));
        orderList.add(orderService.makeOrder(user3, room1, date.getTime(), (short) 3));
        orderList.add(orderService.makeOrder(user3, room2, date.getTime(), (short) 3));
        orderList.add(orderService.makeOrder(user3, room2, date.getTime(), (short) 3));
        orderList.add(orderService.makeOrder(user3, room2, date.getTime(), (short) 3));

        orderList.add(orderService.makeOrder(user4, room4, date.getTime(), (short) 1));
        orderList.add(orderService.makeOrder(user4, room4, date.getTime(), (short) 1));
        orderList.add(orderService.makeOrder(user4, room4, date.getTime(), (short) 1));

        for (Order order : orderList) {
            orderService.confirmOrder(order);
        }
    }
}
