package org.example.Infraestructure.DbManagment.Direccion;

import org.example.Infraestructure.Conections.Conexiones;
import org.example.Infraestructure.Models.CiudadModels;

import java.sql.SQLException;

public class Ciudad {
    private Conexiones conexion;

    public Ciudad(String userBD, String passDB, String hostDB, String portDB, String dataBase){
        conexion = new Conexiones(userBD, passDB, hostDB, portDB, dataBase);
    }

    public String registrarCiudad(CiudadModels ciudad){

        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            boolean execute = conexion.getQuerySQL().execute("INSERT INTO ciudad(" +
                    "'idCiudad', " +
                    "'ciudad', " +
                    "'departamento', " +
                    "'codigoPostal') " +
                    "values('" +
                    ciudad.IdCiudad + "', '" +
                    ciudad.Ciudad + "', '" +
                    ciudad.Departamento + "', '" +
                    ciudad.CodigoPostal + "'))");
            conexion.conexionDB().close();
            return "La ciudad " + ciudad.IdCiudad + " fue registrada correctamente!!!";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String modificarCiudad(CiudadModels ciudad){

        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            boolean execute = conexion.getQuerySQL().execute("UPDATE ciudad SET " +
                    "'idCiudad' = '" + ciudad.IdCiudad + "'," +
                    "'ciudad' = '" + ciudad.Ciudad + "'," +
                    "'departamento' = '" + ciudad.Departamento + "'," +
                    "'codigoPostal' = " + ciudad.CodigoPostal );
            conexion.conexionDB().close();
            return "Los datos de la ciudad " + ciudad.IdCiudad + " fueron modificados correctamente!!!";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CiudadModels consultarCiudad(int id){
        CiudadModels ciudad = new CiudadModels();
        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            conexion.setResultadoQuery(conexion.getQuerySQL().executeQuery("Select * from ciudad where idCiudad = " + id));
            if(conexion.getResultadoQuery().next()){
                ciudad.IdCiudad = conexion.getResultadoQuery().getInt("idCiudad");
                ciudad.Ciudad = conexion.getResultadoQuery().getString("ciudad");
                ciudad.Departamento = conexion.getResultadoQuery().getString("departamento");


                return ciudad;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
