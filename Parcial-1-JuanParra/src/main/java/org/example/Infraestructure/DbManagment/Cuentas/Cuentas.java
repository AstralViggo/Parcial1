package org.example.Infraestructure.DbManagment.Cuentas;

import org.example.Infraestructure.Conections.Conexiones;
import org.example.Infraestructure.Models.CuentasModels;

import java.sql.SQLException;

public class Cuentas {
    private Conexiones conexion;

    public Cuentas(String userBD, String passDB, String hostDB, String portDB, String dataBase){
        conexion = new Conexiones(userBD, passDB, hostDB, portDB, dataBase);
    }

    public String registrarCuentas(CuentasModels cuentas){

        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            boolean execute = conexion.getQuerySQL().execute("INSERT INTO cuentas(" +
                    "'idCuenta', " +
                    "'idCliente', " +
                    "'nroCuenta', " +
                    "'fechaAlta', " +
                    "'tipoCuenta', " +
                    "'estado'," +
                    "'saldo', " +
                    "'nroContrato'," +
                    "'costoMantenimiento'," +
                    "'promedioAcreditacion', " +
                    "'moneda') " +
                    "values('" +
                    cuentas.IdCuenta + "', '" +
                    cuentas.IdCliente + "', '" +
                    cuentas.NroCuenta + "', '" +
                    cuentas.FechaAlta + "', '" +
                    cuentas.TipoCuenta + "', '" +
                    cuentas.Estado + "', '" +
                    cuentas.Saldo + "', '" +
                    cuentas.NroContrato + "', '" +
                    cuentas.CostoMantenimiento + "', '" +
                    cuentas.PromedioAcreditacion + "', '" +
                    cuentas.Moneda + "'))");
            conexion.conexionDB().close();
            return "La Cuenta" + cuentas.IdCuenta + " fue registrada correctamente!!!";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String modificarCuentas(CuentasModels cuentas){

        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            boolean execute = conexion.getQuerySQL().execute("UPDATE cliente SET " +
                    "'idCuenta' = '" + cuentas.IdCuenta + "'," +
                    "'idCliente' = '" + cuentas.IdCliente + "'," +
                    "'nroCuenta' = '" + cuentas.NroCuenta + "'," +
                    "'fechaAlta' = '" + cuentas.FechaAlta + "'," +
                    "'tipoCuenta' = '" + cuentas.TipoCuenta + "'," +
                    "'estado' = '" + cuentas.Estado + "'," +
                    "'saldo' = '" + cuentas.Saldo + "'," +
                    "'nroContrato' = '" + cuentas.NroContrato + "'," +
                    "'costoMantenimiento' = '" + cuentas.CostoMantenimiento + "'," +
                    "'promedioAcreditacion' = '" + cuentas.PromedioAcreditacion + "'," +
                    "'moneda' = " + cuentas.Moneda );
            conexion.conexionDB().close();
            return "Los datos del cliente " + cuentas.IdCuenta + " fueron modificados correctamente!!!";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CuentasModels consultarCuentas(int id){
        CuentasModels cuentas = new CuentasModels();
        try {
            conexion.setQuerySQL(conexion.conexionDB().createStatement());
            conexion.setResultadoQuery(conexion.getQuerySQL().executeQuery("Select * from cuentas where idCuentas = " + id));
            if(conexion.getResultadoQuery().next()){
                cuentas.IdCuenta = conexion.getResultadoQuery().getInt("idCuenta");
                cuentas.IdCliente = conexion.getResultadoQuery().getInt("idCliente");
                cuentas.NroCuenta = conexion.getResultadoQuery().getString("nroCuenta");


                return cuentas;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
