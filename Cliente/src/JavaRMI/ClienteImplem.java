/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaRMI;

import interfaces.InterfaceAS;
import interfaces.InterfaceCliente;
import interfaces.InterfaceSS;
import interfaces.InterfaceTGS;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 *
 * @author Klockner
 */

/*Implementa a interface InterfaceCliente*/
public class ClienteImplem extends UnicastRemoteObject implements InterfaceCliente {
    
    Registry refSN;
    InterfaceAS refAS;
    InterfaceTGS refTGS;
    InterfaceSS refSS;

    public ClienteImplem(Registry refSN) throws RemoteException, NotBoundException {
        this.refSN = refSN;
        //Retorna a referência do servidor AS
        this.refAS = (InterfaceAS) refSN.lookup("ServerAS");
        //Retorna a referência do servidor TGS
        this.refTGS = (InterfaceTGS) refSN.lookup("ServerTGS");
//        //Retorna a referência do servidor SS
//        this.refSS = (InterfaceSS) refSN.lookup("ServerSS");
    }

    @Override
    public void echo(String texto) throws RemoteException {
        refAS.chamar(texto, this);
    }
     
    //Envia uma solicitação m1 de autenticação para o AS
    public void solicitarAutenticacao(String idCliente, int tempoValidade) throws RemoteException {
        Random geradorRandom = new Random();
        int numeroAleatorio = geradorRandom.nextInt();
        
        //Faz o primeiro passo do algoritmo
        refAS.autenticar(idCliente, this, refTGS, tempoValidade, numeroAleatorio);
    }
}
