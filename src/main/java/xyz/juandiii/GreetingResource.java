package xyz.juandiii;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import xyz.juandiii.data.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/hello")
public class GreetingResource {

    @Inject
    DataSource dataSource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {


        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {;

            String sqlQuery = "SELECT * from users";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while(resultSet.next()) {
                        users.add(new User().setUsername(resultSet.getString("username")));
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Response.ok()
                .entity(users)
                .build();
    }
}
