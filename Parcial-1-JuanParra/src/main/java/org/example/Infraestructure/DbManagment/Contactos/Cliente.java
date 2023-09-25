package org.example.Infraestructure.DbManagment.Contactos;
import org.example.Infraestructure.Conections.Conexiones;
import org.example.Infraestructure.Models.ClienteModels;
import java.sql.SQLException;
public class Cliente {
    private Conexiones conexion;

    public Cliente(String userBD, String passDB, String hostDB, String portDB, String dataBase){
        conexion = new Conexiones(userBD, passDB, hostDB, portDB, dataBase);
    }

    public String registrarCliente(ClienteModels cliente){

        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            boolean execute = conexion.getQuerySQL().execute("INSERT INTO cliente(" +
                    "'idCliente', " +
                    "'idPersona', " +
                    "'fechaIngreso', " +
                    "'calificacion', " +
                    "'estado'," +
                    "'idCiudad') " +
                    "values('" +
                    cliente.IdCliente + "', '" +
                    cliente.IdPersona+ "', '" +
                    cliente.FechaIngreso + "', '" +
                    cliente.Calificacion + "', '" +
                    cliente.Estado + "'))");
            conexion.conexionDB().close();
            return "El cliente " + cliente.IdCliente + " fue registrado correctamente!!!";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String modificarCliente(ClienteModels cliente){

        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            boolean execute = conexion.getQuerySQL().execute("UPDATE cliente SET " +
                    "'idCliente' = '" + cliente.IdCliente + "'," +
                    "'idPersona' = '" + cliente.IdPersona + "'," +
                    "'fechaIngreso' = '" + cliente.FechaIngreso + "'," +
                    "'calificacion' = '" + cliente.Calificacion + "'," +
                    "'estado' = " + cliente.Estado);
            conexion.conexionDB().close();
            return "Los datos del cliente " + cliente.IdCliente + " fueron modificados correctamente!!!";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ClienteModels consultarCliente(int id){
        ClienteModels cliente = new ClienteModels();
        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            conexion.setResultadoQuery(conexion.getQuerySQL().executeQuery("Select * from cliente where idClinete = " + id));
            if(conexion.getResultadoQuery().next()){
                cliente.IdCliente = conexion.getResultadoQuery().getInt("idCliente");
                cliente.IdPersona = conexion.getResultadoQuery().getInt("idPersona");
                cliente.FechaIngreso = conexion.getResultadoQuery().getString("fechaIngreso");


                return cliente;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
