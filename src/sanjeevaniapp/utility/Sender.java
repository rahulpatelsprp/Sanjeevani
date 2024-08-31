/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.utility;

/**
 *
 * @author hp
 */
public interface Sender {
    boolean send(String number,String data)throws Exception;
}
