/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import SO.LoginSO;
import javax.jws.*;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author milos
 */
@WebService(endpointInterface = "ws.ILogin")
public class Login implements ILogin {

    @Override
    public Response login(Request request) {
        LoginSO so = new LoginSO();
        return so.login(request);
    }

}
