/**
 * Created by ICOP on 2017-10-24.
 */

var PATH = "http://localhost:8080/";
var CODE = "icop-db";

var MainUrl = {
    LOGIN: PATH + CODE +  "/User/Login",
    VERIFY: PATH + CODE + "/User/UserVerify",
    REGISTER: PATH + CODE + "/User/Register",
}
module.exports = MainUrl;