/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Klockner
 */
public class Cliente {
    
    /**
     * @param args the command line arguments
     * @throws java.rmi.NotBoundException
     */
    public static void main(String[] args) throws NotBoundException {
        try {
            //Para iniciar um serviço de nomes no servidor
            Registry refSN = LocateRegistry.getRegistry("localhost", 1111);
            ClienteImplem clienteImplem = new ClienteImplem(refSN);
            
            //Id cliente e tempo de validade
            clienteImplem.solicitarAutenticacao("Gabriel", 10);
            
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
