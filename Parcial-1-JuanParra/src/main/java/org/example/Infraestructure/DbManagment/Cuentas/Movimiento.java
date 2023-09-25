package org.example.Infraestructure.DbManagment.Cuentas;

import org.example.Infraestructure.Conections.Conexiones;
import org.example.Infraestructure.Models.CuentasModels;
import org.example.Infraestructure.Models.MovimientoModels;

import java.sql.SQLException;

public class Movimiento {
    private Conexiones conexion;

    public Movimiento(String userBD, String passDB, String hostDB, String portDB, String dataBase){
        conexion = new Conexiones(userBD, passDB, hostDB, portDB, dataBase);
    }

    public String registrarMovimiento(MovimientoModels movimiento){
        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            boolean execute = conexion.getQuerySQL().execute("INSERT INTO movimiento(" +
                    "'idMovimiento', " +
                    "'idCuenta', " +
                    "'fechaMovimiento', " +
                    "'tipoMovimiento', " +
                    "'saldoAnterior', " +
                    "'saldoActual'," +
                    "'montoMovimiento', " +
                    "'cuentaOrigen'," +
                    "'cuentaDestino'," +
                    "'canal') " +
                    "values('" +
                    movimiento.IdMovimiento + "', '" +
                    movimiento.IdCuenta + "', '" +
                    movimiento.FechaMovimiento + "', '" +
                    movimiento.TipoMovimiento + "', '" +
                    movimiento.SaldoAnterior + "', '" +
                    movimiento.SaldoActual + "', '" +
                    movimiento.MontoMovimiento + "', '" +
                    movimiento.CuentaOrigen + "', '" +
                    movimiento.CuentaDestino + "', '" +
                    movimiento.Canal + "'))");
            conexion.conexionDB().close();
            return "El movimiento" + movimiento.IdMovimiento + " fue registrado correctamente!!!";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String modificarMovimiento(MovimientoModels movimiento){

        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            boolean execute = conexion.getQuerySQL().execute("UPDATE movimiento SET " +
                    "'idMovimiento' = '" + movimiento.IdMovimiento + "'," +
                    "'idCuenta' = '" + movimiento.IdCuenta + "'," +
                    "'fechaMovimiento' = '" + movimiento.FechaMovimiento + "'," +
                    "'tipoMovimiento' = '" + movimiento.TipoMovimiento + "'," +
                    "'saldoAnterior' = '" + movimiento.SaldoAnterior + "'," +
                    "'saldoActual' = '" + movimiento.SaldoActual + "'," +
                    "'montoMovimiento' = '" + movimiento.MontoMovimiento + "'," +
                    "'cuentaOrigen' = '" + movimiento.CuentaOrigen + "'," +
                    "'cuentaDestino' = '" + movimiento.CuentaDestino + "'," +
                    "'canal' = " + movimiento.Canal );
            conexion.conexionDB().close();
            return "Los datos del movimiento " + movimiento.IdMovimiento + " fueron modificados correctamente!!!";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MovimientoModels consultarMovimiento(int id){
        MovimientoModels movimiento = new MovimientoModels();
        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            conexion.setResultadoQuery(conexion.getQuerySQL().executeQuery("Select * from movimiento where idMovimiento = " + id));
            if(conexion.getResultadoQuery().next()){
                movimiento.IdMovimiento = conexion.getResultadoQuery().getInt("idMovimieto");
                movimiento.IdCuenta = conexion.getResultadoQuery().getInt("idCuenta");
                movimiento.FechaMovimiento = conexion.getResultadoQuery().getString("fechaMovimiento");


                return movimiento;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
